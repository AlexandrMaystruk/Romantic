package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeInteractorImpl @Inject constructor(private val executor: ThreadExecutor, val repository: HikesRepository) :
    HikeInteractor {


    override fun provideHikeParticipants(hikeId: Long): Single<List<Participant>> {
        return repository.downloadParticipantsFromFireStore(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun downloadHikes(): Completable {
        return repository.downloadHikeFromFireStore().flatMapCompletable {
            return@flatMapCompletable Observable.fromIterable(it)
                .concatMapCompletable { hike ->
                    return@concatMapCompletable repository.downloadParticipantsFromFireStore(hike.id)
                        .flatMapCompletable {
                            repository.cashHike(hike.apply { group.addAll(it) }).subscribeOn(executor.mainExecutor)
                        }
                }
        }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideUserHikes(): Single<List<Hike>> {
        return repository.provideUserHikes()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideHikesByType(typeHike: TypeHike): Single<Pair<List<Hike>, Int>> {
        return repository.provideHikes(typeHike)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }

    override fun leaveFromHikeGroup(hike: Hike): Completable {
        return repository.leaveFromHikeGroup(hikeId = hike.id)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun deleteHike(hike: Hike): Completable {
        return repository.removeHike(hikeId = hike.id)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }
}