package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike

interface HikeInteractor{

    fun provideHikes(typeHike: TypeHike?): Single<List<Hike>>

}