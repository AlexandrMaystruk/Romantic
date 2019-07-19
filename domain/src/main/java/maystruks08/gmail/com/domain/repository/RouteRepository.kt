package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Route


interface RouteRepository {

    fun getRoutes(hikeId: Long): Single<List<Route>>

    fun getRoute(routeId: Long): Single<Route>

    fun saveRote(hikeId: Long, route: Route): Single<Route>

    fun saveRotes(hikeId: Long, routes: List<Route>): Single<List<Route>>

    fun removeRoute(hikeId: Long, routeId: Long): Completable

}