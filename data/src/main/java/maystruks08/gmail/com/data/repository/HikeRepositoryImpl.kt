package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val pref: AuthPreferences
) : HikesRepository {

    override fun createNewHike(hike: Hike): Completable {
        return Completable.fromAction { hikeDAO.insertAll(hikeMapper.toHikeTable(hike)) }
            .andThen(api.saveHikeToFirestore(hike))
            .andThen(api.setUserToHikeGroup(getCurrentUser(), hike.id.toString(), UserPost.BOSS.name))
    }

    override fun downloadHikeFromFireStore(): Single<List<Hike>> {
        return api.getAllHikesFromFireStore().flatMapSingle { snapshot ->
            val result = mutableListOf<Hike>()
            snapshot.documents.map {
                val hike = it.toObject(Hike::class.java)
                if (hike != null) {
                    result.add(hike)
                }
            }
            Single.just(result)
        }

    }

    override fun saveHikeToDb(hike: Hike): Completable {
        return Completable.fromAction { hikeDAO.insertAll(hikeMapper.toHikeTable(hike)) }
    }

    override fun saveHikesToDb(hikes: List<Hike>): Completable {
        return Completable.fromAction { hikeDAO.insertAll(hikeMapper.toHikeTableList(hikes)) }
    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return if (typeHike == null) {
            hikeDAO.getHikes().map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, 0)
            }
        } else {
            hikeDAO.getHikesByType(typeHike.type.toString()).map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, typeHike.type)
            }
        }
    }

    override fun getCurrentUser(): User {
        return pref.getCurrentUser()!!
    }

    override fun provideUserHikes(currentUser: User): Single<List<Hike>> {
        return hikeDAO.getHikesByUserId(currentUser.id).map { list ->
            list.map { hikeMapper.toHikeFromTable(it) }

        }
    }
}