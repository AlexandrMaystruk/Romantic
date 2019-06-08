package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.RouteRepository
import javax.inject.Inject

class RouteInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
   private val routeRepository: RouteRepository
) : RouteInteractor {

    override fun getHikeRoutes(hikeId: Long): Single<List<Route>> {
        return routeRepository.getRoutes(hikeId)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }
}