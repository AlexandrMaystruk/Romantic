package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.FireStoreHike
import maystruks08.gmail.com.domain.entity.firebase.FireStoreParticipant
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantMapper: ParticipantMapper,
    private val pref: AuthPreferences
) : HikesRepository {

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

    override fun addParticipant(hikeId: Long, participant: Participant): Completable {
        return Completable.fromAction {
            hikeDAO.addUserToHikeGroup(hikeId, participantMapper.toParticipantTable(participant))
        }
    }

    override fun getCurrentUserFromPref(): User {
        return pref.getCurrentUser()!!
    }

    override fun downloadHikeFromFireStore(): Single<List<Hike>> {
        return api.getAllHikes().flatMapSingle { snapshot ->
            val hikes = mutableListOf<Hike>()
            snapshot.documents.map {
                val fireBaseHike = it.toObject(FireStoreHike::class.java)
                if (fireBaseHike != null) {
                    val hike = hikeMapper.toHike(fireBaseHike)
                    hikes.add(hike)
                }
            }
            Single.just(hikes)
        }
    }


    override fun downloadParticipantsFromFireStore(hikeId: Long): Single<List<Participant>> {
        return api.getHikeGroup(hikeId).flatMapSingle { snapshot ->
            val participants = mutableListOf<Participant>()
            val participant = snapshot.toObject(FireStoreParticipant::class.java)
            if (participant != null) {
                participants.add(participantMapper.toParticipant(participant))
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
}