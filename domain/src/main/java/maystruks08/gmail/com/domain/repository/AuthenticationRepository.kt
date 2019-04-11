package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface AuthenticationRepository {

    fun login(email: String, password: String): Completable

    fun createFireBaseUser(email: String, password: String, displayName: String): Single<User>

    fun getCurrentUser(): Completable

    fun addUserToDb(user: User): Completable

    fun addUserToFireStoreDb(user: User): Completable
}