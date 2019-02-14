package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable

interface LogOutInteractor {

    fun logout(): Completable

}