package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject

class ParticipantInteractorImpl @Inject constructor(
    private val participantRepository: ParticipantRepository,
    private val executor: ThreadExecutor) : ParticipantInteractor {

    override fun getHikeParticipant(hikeId: String): Single<List<User>> {
        return participantRepository.getHikeParticipant(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}