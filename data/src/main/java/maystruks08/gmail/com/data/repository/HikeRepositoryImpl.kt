package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireBaseApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireBaseApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper
) : HikesRepository {

    override fun createNewHike(hike: Hike): Completable {
        return Completable.complete()

//       return Completable.fromAction {
//           hikeDAO.insertAll(hikeMapper.toHikeTable(hike))
//       }
    }

    override fun downloadHikeData(): Single<List<Hike>> {
        return Single.just(api.getAllHikes())
    }

    override fun saveHikesToDb(hikes: List<Hike>): Completable {
        return Completable.complete()
//        return Completable.fromAction { hikeDAO.insertAll(hikeMapper.toHikeTableList(hikes)) }
    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return if (typeHike == null) {
            Single.just(Pair(api.getAllHikes(), 0))
        } else {
            Single.just(Pair(api.getAllHikes().filter { it.typeHike == typeHike }, typeHike.type))
        }
    }

    override fun getCurrentUser(): Single<User> {
        return Single.just(User("", "", "", 23, 23,65, 235, ""))
    }

    override fun provideUserHikes(currentUser: User): Single<List<Hike>> {
        return  Single.just(api.getAllHikes())
    }
}