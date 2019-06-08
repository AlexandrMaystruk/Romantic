package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType

interface BuildRouteInteractor {

    fun buildRoute(id: Long, type: RouteType, listPoint: List<Point>): Single<Route>

    fun addNewPoint(route: Route, point: Point): Single<Route>

    fun removePoint(route: Route, point: Point): Single<Route>
}