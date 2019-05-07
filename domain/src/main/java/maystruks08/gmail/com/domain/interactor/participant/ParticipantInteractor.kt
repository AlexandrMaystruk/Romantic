package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User

interface ParticipantInteractor {

    fun getHikeParticipants(hikeId: Long): Single<List<Participant>>

    fun getUsers(): Single<List<User>>

    fun addParticipantsToHikeGroup(hikeId: Long, participants: List<Participant>): Completable

    fun removeParticipant(hikeId: Long, participants: Participant): Completable
}