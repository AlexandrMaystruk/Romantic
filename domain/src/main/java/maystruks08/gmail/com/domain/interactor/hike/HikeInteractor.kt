package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike

interface HikeInteractor{

    fun downloadHikes(): Completable

    fun provideHikesByType(typeHike: TypeHike): Single<Pair<List<Hike>, Int>>

    fun provideUserHikes(): Single<List<Hike>>

    fun provideHikeParticipants(hikeId: Long): Single<List<Participant>>

    fun leaveFromHikeGroup(hike: Hike): Completable

    fun deleteHike(hike: Hike): Completable

}