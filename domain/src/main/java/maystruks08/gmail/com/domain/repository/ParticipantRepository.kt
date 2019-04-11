package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User

interface ParticipantRepository {

    fun getHikeParticipant(hikeId: String): Single<List<Participant>>

    fun getAllParticipantsFromFireStoreByHikeId(hikeId: Long): Single<List<Participant>>


    fun setUserToHikeGroup(user: User, hikeId: Long, post: String): Completable

    fun getAllUserFromFireStore(): Single<List<User>>
}