package maystruks08.gmail.com.domain.interactor.main

import io.reactivex.Completable

interface LogOutInteractor {

    fun logout(): Completable

}