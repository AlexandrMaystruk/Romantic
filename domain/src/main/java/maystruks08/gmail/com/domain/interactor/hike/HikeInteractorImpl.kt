package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeInteractorImpl @Inject constructor(private val executor: ThreadExecutor, val repository: HikesRepository) :
    HikeInteractor {


    override fun createNewHike(hike: Hike): Completable {
        return repository.createNewHike(hike)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }


    override fun getHikesFromFireStore(): Completable {
        return repository.downloadHikeFromFireStore()
            .flatMapCompletable {
                repository.saveHikesToDb(it)
            }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideUserHikes(): Single<List<Hike>> {
        return repository.provideUserHikes(repository.getCurrentUser())
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return repository.provideHikes(typeHike)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}