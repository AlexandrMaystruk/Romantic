package maystruks08.gmail.com.domain.interactor.participant

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject

class ParticipantInteractorImpl @Inject constructor(
    private val participantRepository: ParticipantRepository,
    private val executor: ThreadExecutor
) : ParticipantInteractor {

    override fun getUsers(): Single<List<User>> {
        return participantRepository.getAllUsers()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun getHikeParticipants(hikeId: Long): Single<List<Participant>> {
        return participantRepository.getHikeParticipant(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun addParticipantsToHikeGroup(hikeId: Long, participants: List<Participant>): Completable {
        return Observable.fromIterable(participants)
            .flatMapCompletable {
                participantRepository.setParticipantHikeGroup(hikeId, it)
            }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun removeParticipant(hikeId: Long, participants: Participant): Completable {
        return participantRepository.removeParticipant(hikeId, participants)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}