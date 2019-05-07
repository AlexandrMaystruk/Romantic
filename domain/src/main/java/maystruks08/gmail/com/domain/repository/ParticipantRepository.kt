package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost

interface ParticipantRepository {

    fun getAllUsers(): Single<List<User>>

    fun getHikeParticipant(hikeId: Long): Single<List<Participant>>

    fun setUserToHikeGroup(hikeId: Long,user: User, post: UserPost): Completable

    fun setParticipantHikeGroup(hikeId: Long,participant: Participant): Completable

    fun removeParticipant(hikeId: Long,participants: Participant): Completable
}