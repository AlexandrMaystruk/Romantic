package maystruks08.gmail.com.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.entity.firebase.FireStoreParticipant
import maystruks08.gmail.com.domain.event.UpdateBus
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject


class ParticipantRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val participantDAO: ParticipantDAO,
    private val participantMapper: ParticipantMapper,
    private val userDao: UserDAO,
    private val userMapper: UserMapper,
    private val updateBus: UpdateBus
) : ParticipantRepository {

    override fun getAllUsers(): Single<List<User>> {
        return userDao.getUsers().flatMap { list ->
            Single.just(list.map { userMapper.userTableToUser(it) })
        }
    }

    override fun getHikeParticipant(hikeId: Long): Single<List<Participant>> {
        return participantDAO.getParticipantsByHikeId(hikeId)
            .doAfterSuccess {
                getRemoteHikeParticipants(hikeId, it)
            }
            .map { list -> list.map { participantMapper.toParticipant(it) } }
    }

    override fun setUserToHikeGroup(hikeId: Long, user: User, post: UserPost): Completable {
        return Completable.fromAction {
            participantDAO.addParticipant(participantMapper.toParticipantTable(Participant(post,hikeId, user)))
        }
    }

    override fun setParticipantHikeGroup(hikeId: Long, participant: Participant): Completable {
        return Completable.fromAction {
            participantDAO.addParticipant(participantMapper.toParticipantTable(participant))
        }
    }


    override fun removeParticipant(hikeId: Long, participants: Participant): Completable {
        return Completable.fromAction { participantDAO.delete(participantMapper.toParticipantTable(participants)) }
            .andThen(api.removeParticipantFromGroup(hikeId, participantMapper.toFireStoreParticipant(participants)))
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
            userDao.dropTable()
            userDao.insert(userMapper.userListToListUserTable(result))
            Single.just(result)
        }
    }


    private fun getRemoteHikeParticipants(
        hikeId: Long,
        cashList: List<ParticipantTable> = mutableListOf()
    ): Single<List<Participant>> {
        return api.getHikeGroup(hikeId).flatMapSingle { snapshot ->
            val remote = mutableListOf<Participant>()

                val participant = snapshot.toObject(FireStoreParticipant::class.java)
                if (participant != null) {
                    remote.add(participantMapper.toParticipant(participant))
                    Log.d("Participant", "User post  ${participant.id}")

            }

            val remoteParticipantTable = remote.map { participantMapper.toParticipantTable(it) }
            if (compareList(remoteParticipantTable, cashList)) {
                participantDAO.delete(cashList)
                participantDAO.addUsersToHikeGroup(hikeId, remoteParticipantTable)
                updateBus.postUpdateParticipantEvent(remote)
            }

            Single.just(remote)
        }
    }

    private fun compareList(source: List<ParticipantTable>, actual: List<ParticipantTable>): Boolean {
        return source.size == actual.size
    }
}