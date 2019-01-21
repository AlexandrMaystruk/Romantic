package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface AuthenticationRepository {

    fun singup(email: String, password: String, displayName: String): Single<User>

    fun login(email: String, password: String): Completable

    fun logout(): Completable
}