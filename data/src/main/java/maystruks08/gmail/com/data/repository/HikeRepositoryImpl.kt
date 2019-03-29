package maystruks08.gmail.com.data.repository

import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireBaseApi
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(private val api: FireBaseApi) : HikesRepository {

    override fun provideHikes(typeHike: TypeHike?): Single<List<Hike>> {
        return if (typeHike == null) {
            Single.just(api.hikeList)
        } else {
            Single.just(api.hikeList.filter { it.typeHike == typeHike })
        }
    }
}