package maystruks08.gmail.com.domain.repository

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface UserRepository {

    fun getHikeParticipant(): Single<User>
}