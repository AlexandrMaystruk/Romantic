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


    override fun downloadHikeData(): Completable {
        return repository.downloadHikeData()
            .flatMapCompletable {
                repository.saveHikesToDb(it)
            }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideUserHikes(): Single<List<Hike>> {
        return repository.getCurrentUser()
            .flatMap {
                repository.provideUserHikes(it)
            }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return repository.provideHikes(typeHike)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}