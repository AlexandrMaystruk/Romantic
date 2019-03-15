package maystruks08.gmail.com.domain.repository

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike

interface HikesRepository {

    fun provideHikes(typeHike: TypeHike?): Single<List<Hike>>
}