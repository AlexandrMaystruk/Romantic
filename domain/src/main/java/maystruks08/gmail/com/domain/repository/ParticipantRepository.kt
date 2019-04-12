package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost

interface ParticipantRepository {

    fun getHikeParticipant(hikeId: String): Single<List<Participant>>

    fun getAllParticipantsFromFireStoreByHikeId(hikeId: Long): Single<List<Participant>>


    fun setUserToHikeGroup(user: User, hikeId: Long, post: UserPost): Completable

    fun getAllUserFromFireStore(): Single<List<User>>
}