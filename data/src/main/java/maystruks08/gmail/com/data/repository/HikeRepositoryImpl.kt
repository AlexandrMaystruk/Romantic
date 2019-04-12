package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.FireBaseHike
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantMapper: ParticipantMapper,
    private val pref: AuthPreferences
) : HikesRepository {

    override fun saveToLocalDB(hike: Hike): Completable {
        return Completable.fromAction { hikeDAO.insert(hikeMapper.toHikeTable(hike)) }
    }

    override fun saveToLocalDB(hikes: List<Hike>): Completable {
        return Completable.fromAction { hikeDAO.insert(hikeMapper.toHikeTableList(hikes)) }
    }

    override fun addParticipant(participant: Participant): Completable {
        return hikeDAO.addUserToHikeGroup(participantMapper.toParticipantTable(participant))
    }

    override fun addBossToHike(hikeId: Long): Completable {
        val boss = Participant(UserPost.BOSS, hikeId, getCurrentUserFromPref())
        return hikeDAO.addUserToHikeGroup(participantMapper.toParticipantTable(boss))
    }

    override fun getCurrentUserFromPref(): User {
        return pref.getCurrentUser()!!
    }

    override fun downloadHikeFromFireStore(): Single<List<Hike>> {
        return api.getAllHikes().flatMapSingle { snapshot ->
            val result = mutableListOf<Hike>()
            snapshot.documents.map {
                val fireBaseHike = it.toObject(FireBaseHike::class.java)
                if (fireBaseHike != null) {
                    result.add(hikeMapper.toHike(fireBaseHike))
                }
            }
            Single.just(result)
        }

    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return if (typeHike == null) {//todo fix query
            hikeDAO.getHikesByUserId(getCurrentUserFromPref().id).map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, 0)
            }
        } else {//todo fix query
            hikeDAO.getHikesByType(getCurrentUserFromPref().id, typeHike.type.toString()).map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, typeHike.type)
            }
        }
    }

    //todo fix query
    override fun provideUserHikes(currentUser: User): Single<List<Hike>> {
        return hikeDAO.getHikesByUserId(currentUser.id).map { list ->
            list.map { hikeMapper.toHikeFromTable(it) }

        }
    }
}