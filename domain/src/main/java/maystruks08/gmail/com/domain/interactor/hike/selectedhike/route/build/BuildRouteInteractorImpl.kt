package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.RouteBuilder
import javax.inject.Inject

class BuildRouteInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    private val routeBuilder: RouteBuilder
) : BuildRouteInteractor {

    override fun buildRoute(id: Long, type: RouteType, listPoint: List<Point>): Single<Route> {
        return routeBuilder.buildRout(id, type, listPoint)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }

    override fun addNewPoint(route: Route, point: Point): Single<Route> {
        return routeBuilder.addNewPoint(route, point)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }

    override fun removePoint(route: Route, point: Point): Single<Route> {
        return routeBuilder.removePoint(route, point)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}