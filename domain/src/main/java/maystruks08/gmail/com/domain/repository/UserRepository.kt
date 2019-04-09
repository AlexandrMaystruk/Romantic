package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface UserRepository {

    fun getHikeParticipant(hikeId: String): Single<List<User>>

    fun addNewUser(user: User):Completable

    fun addNewUserToFireStore(user: User): Completable
}