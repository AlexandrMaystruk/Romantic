package maystruks08.gmail.com.domain.interactor.hike.create

import io.reactivex.Completable
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class CreateHikeInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    val repository: HikesRepository
) :
    CreateHikeInteractor {


    override fun createNewHike(hike: Hike): Completable {
        return repository.saveToLocalDB(hike)
            .andThen(repository.addBossToHike(hikeId = hike.id))
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}