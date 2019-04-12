package maystruks08.gmail.com.domain.interactor.main

import io.reactivex.Completable
import io.reactivex.Single

interface RootInteractor {

    fun logout(): Completable

    fun uploadHikes(): Single<Int>

    fun updateHikes(): Single<Int>

    fun updateParticipants(): Single<Int>

    fun uploadParticipants(): Single<Int>

}