package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike

interface HikeInteractor{

    fun downloadHikeData(): Completable

    fun provideHikes(typeHike: TypeHike? = null): Single<Pair<List<Hike>, Int>>

    fun provideUserHikes(): Single<List<Hike>>

}