package maystruks08.gmail.com.data.repository

import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.POJOHike
import maystruks08.gmail.com.domain.entity.firebase.POJOParticipant
import maystruks08.gmail.com.domain.event.UpdateBus
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantDAO: ParticipantDAO,
    private val participantMapper: ParticipantMapper,
    private val userDao: UserDAO,
    private val userMapper: UserMapper,
    private val pref: AuthPreferences,
    private val updateBus: UpdateBus

) : HikesRepository {

    override fun getCurrentUserFromPref(): User {
        return pref.getCurrentUser()!!
    }

    override fun createNewHike(hike: Hike): Completable {
        val boss = Participant(UserPost.BOSS, hike.id, getCurrentUserFromPref())
        return Completable.fromAction {
            hikeDAO.createHike(hikeMapper.toHikeTable(hike), participantMapper.toParticipantTable(boss))
        }
    }

    override fun cashHike(hike: Hike): Completable {
        return Completable.fromAction {
            hikeDAO.cashHike(hikeMapper.toHikeTable(hike), participantMapper.toParticipantTableList(hike.group))
        }
    }

    override fun cashHikes(hikes: List<Hike>): Completable {
        return Completable.fromAction {
            hikes.forEach {
                hikeDAO.cashHike(hikeMapper.toHikeTable(it), participantMapper.toParticipantTableList(it.group))
            }
        }
    }

    override fun downloadHikeFromFireStore(): Single<List<Hike>> {
        return api.getAllHikes().flatMapSingle { snapshot ->
            val hikes = mutableListOf<Hike>()
            snapshot.documents.map {
                val fireBaseHike = it.toObject(POJOHike::class.java)
                if (fireBaseHike != null) {
                    val hike = hikeMapper.toHike(fireBaseHike)
                    hikes.add(hike)
                }
            }
            Single.just(hikes)
        }
    }

    override fun cashParticipant(hikeId: Long, participant: Participant): Completable {
        return Completable.fromAction {
            hikeDAO.addUserToHikeGroup(hikeId, participantMapper.toParticipantTable(participant))
        }
    }


    override fun downloadParticipantsFromFireStore(hikeId: Long): Single<List<Participant>> {
        return api.getHikeGroup(hikeId).flatMapSingle { snapshot ->
            val participants = mutableListOf<Participant>()
            snapshot.data?.values?.forEach { any ->
                (any as?  Map<String, Any>)?.let {
                    val participant = POJOParticipant.fromHashMap(it)
                    participants.add(participantMapper.toParticipant(participant))
                }
            }
            return@flatMapSingle Single.just(participants)
        }
    }

    override fun provideHikes(typeHike: TypeHike): Single<Pair<List<Hike>, Int>> {
        return hikeDAO.getHikes(getCurrentUserFromPref().id, typeHike.type).map { list ->
            val result = list.map { hikeMapper.toHikeFromTable(it) }
            Pair(result, typeHike.type)
        }
    }

    override fun provideUserHikes(): Single<List<Hike>> {
        return hikeDAO.getHikesByUserId(getCurrentUserFromPref().id).map { list ->
            list.map { hikeMapper.toHikeFromTable(it) }
        }
    }

    //user
    override fun getUsers(): Single<List<User>> {
        return userDao.getUsers().flatMap { list ->
            Single.just(list.map { userMapper.userTableToUser(it) })
        }
    }

    override fun updateCashedUsers(cashUsers: List<User>): Single<List<User>> {
        return getRemoteUsers().flatMap { remoteUsers ->
            if (compareUserList(remoteUsers, cashUsers)) {
                Completable.fromAction { userDao.delete(cashUsers.map { userMapper.authToUserTable(it) }) }
                    .andThen(Completable.fromAction { userDao.insert(remoteUsers.map { userMapper.authToUserTable(it) }) })
                    .andThen(Single.just(remoteUsers))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.error(Throwable("All users is cashed"))
            }
        }
    }


    //participant
    override fun getHikeParticipants(hikeId: Long): Single<List<Participant>> {
        return participantDAO.getParticipantsByHikeId(hikeId)
            .doAfterSuccess { cashParticipant ->
                downloadParticipantsFromFireStore(hikeId).flatMap { remoteParticipant ->
                    val remoteParticipantTable = remoteParticipant.map { participantMapper.toParticipantTable(it) }
                    if (compareList(remoteParticipantTable, cashParticipant)) {
                        participantDAO.delete(cashParticipant)
                        participantDAO.addUsersToHikeGroup(hikeId, remoteParticipantTable)
                        updateBus.postUpdateParticipantEvent(remoteParticipant)
                    }
                    Single.just(remoteParticipant)
                }

            }
            .map { list -> list.map { participantMapper.toParticipant(it) } }
    }

    override fun setUserToHikeGroup(hikeId: Long, user: User, post: UserPost): Completable {
        return Completable.fromAction {
            val participant = participantMapper.toParticipantTable(Participant(post, hikeId, user))
            participantDAO.addUserToHikeGroup(hikeId, participant)
        }
    }

    override fun setParticipantHikeGroup(hikeId: Long, participant: Participant): Completable {
        return Completable.fromAction {
            participantDAO.addUserToHikeGroup(hikeId, participantMapper.toParticipantTable(participant))
        }
    }

    override fun setParticipantsToHikeGroup(hikeId: Long, participants: List<Participant>): Completable {
        return Completable.fromAction {
            participantDAO.addUsersToHikeGroup(hikeId, participantMapper.toParticipantTableList(participants))
        }
    }

    override fun removeParticipantFromHikeGroup(hikeId: Long, participant: Participant): Completable {
        return Completable.fromAction {
            participantDAO.deleteUserFromHikeGroup(participantMapper.toParticipantTable(participant))
        }.andThen(participantDAO.getGroupCountByHikeId(hikeId))
            .flatMapCompletable {
                if (it == 0) {
                    Completable.fromAction {
                        hikeDAO.deleteHike(hikeId)
                    }.andThen(api.deleteHike(hikeId))
                } else {
                    api.removeParticipantFromGroup(hikeId, participantMapper.toFireStoreParticipant(participant))
                }
            }
    }

    override fun leaveFromHikeGroup(hikeId: Long): Completable {
        val currentUser = getCurrentUserFromPref()
        return Completable.fromAction {
            participantDAO.deleteUserFromHikeGroup(currentUser.id)
        }.andThen(participantDAO.getGroupCountByHikeId(hikeId))
            .flatMapCompletable {
                if (it == 0) {
                    Completable.fromAction {
                        hikeDAO.deleteHike(hikeId)
                    }.andThen(api.deleteHike(hikeId))
                } else {
                    api.removeParticipantFromGroup(hikeId, currentUser.id)
                }
            }
    }

    override fun removeHike(hikeId: Long): Completable {
        return participantDAO.getParticipantsByHikeId(hikeId)
            .flatMapCompletable {
                return@flatMapCompletable Completable.fromAction {
                    it.forEach {
                        participantDAO.delete(it)
                    }
                }.andThen(
                    Completable.fromAction {
                        hikeDAO.deleteHike(hikeId)
                    }
                ).andThen(api.deleteHike(hikeId))
            }
    }

    private fun getRemoteUsers(): Single<List<User>> {
        return api.getUsers().flatMapSingle { snapshot ->
            val result = mutableListOf<User>()
            snapshot.documents.map {
                val user = it.toObject(User::class.java)
                if (user != null) {
                    result.add(user)
                }
            }
            Single.just(result)
        }
    }

    private fun compareList(source: List<ParticipantTable>, actual: List<ParticipantTable>): Boolean {
        return source.size != actual.size
    }

    private fun compareUserList(source: List<User>, actual: List<User>): Boolean {
        return source.size != actual.size
    }
}