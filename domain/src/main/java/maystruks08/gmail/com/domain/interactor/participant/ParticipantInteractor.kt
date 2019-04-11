package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User

interface ParticipantInteractor {

    fun getHikeParticipants(hikeId: String): Single<List<Participant>>

    fun getUsers(): Single<List<User>>
}