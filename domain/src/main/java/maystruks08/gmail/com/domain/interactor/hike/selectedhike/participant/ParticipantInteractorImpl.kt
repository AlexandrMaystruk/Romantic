package maystruks08.gmail.com.domain.interactor.hike.selectedhike.participant

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class ParticipantInteractorImpl @Inject constructor(
    private val participantRepository: HikesRepository,
    private val executor: ThreadExecutor
) : ParticipantInteractor {

    override fun updateUserCash(localCash: List<User>): Single<List<User>> {
        return participantRepository.updateCashedUsers(localCash)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }

    override fun getUsers(): Single<List<User>> {
        return participantRepository.getUsers()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun getHikeParticipants(hikeId: Long): Single<List<Participant>> {
        return participantRepository.getHikeParticipants(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun addParticipantsToHikeGroup(hikeId: Long, participants: List<Participant>): Completable {
        return participantRepository.setParticipantsToHikeGroup(hikeId, participants)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun removeParticipant(hikeId: Long, participants: Participant): Completable {
        return participantRepository.removeParticipantFromHikeGroup(hikeId, participants)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}