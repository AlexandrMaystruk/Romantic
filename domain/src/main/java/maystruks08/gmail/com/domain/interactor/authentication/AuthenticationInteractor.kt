package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable

interface AuthenticationInteractor {

    fun singUp(email: String, password: String, displayName: String): Completable

    fun login(email: String, password: String): Completable

    fun logout(): Completable

    fun getCurrentUser(): Completable
}