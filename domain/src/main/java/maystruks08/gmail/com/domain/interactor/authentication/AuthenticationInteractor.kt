package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface AuthenticationInteractor {

    fun singup(email: String, password: String, displayName: String): Completable

    fun login(email: String, password: String): Completable

    fun logout(): Completable
}