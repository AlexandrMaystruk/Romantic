package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User

interface ParticipantInteractor {

    fun getHikeParticipant(hikeId: String): Single<List<User>>
}