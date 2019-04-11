package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject

class ParticipantInteractorImpl @Inject constructor(
    private val participantRepository: ParticipantRepository,
    private val executor: ThreadExecutor) : ParticipantInteractor {


    override fun getUsers(): Single<List<User>> {
        return participantRepository.getAllUserFromFireStore()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun getHikeParticipants(hikeId: String): Single<List<Participant>> {
        return participantRepository.getHikeParticipant(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}