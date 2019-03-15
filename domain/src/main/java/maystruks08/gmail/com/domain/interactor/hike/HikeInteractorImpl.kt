package maystruks08.gmail.com.domain.interactor.hike

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeInteractorImpl @Inject constructor(private val executor: ThreadExecutor, val repository: HikesRepository) :
    HikeInteractor {
    override fun provideHikes(typeHike: TypeHike?): Single<List<Hike>> {
        return repository.provideHikes(typeHike)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}