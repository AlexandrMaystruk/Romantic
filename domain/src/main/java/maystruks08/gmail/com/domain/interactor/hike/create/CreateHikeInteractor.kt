package maystruks08.gmail.com.domain.interactor.hike.create

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike

interface CreateHikeInteractor{

    fun createNewHike(hike: Hike): Completable

}