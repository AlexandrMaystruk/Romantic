package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.UserRepository
import javax.inject.Inject

class ParticipantInteractorImpl @Inject constructor(
    private val participantRepository: UserRepository,
    private val executor: ThreadExecutor) : ParticipantInteractor {

    override fun getHikeParticipant(): Single<User> {
        return participantRepository.getHikeParticipant()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}