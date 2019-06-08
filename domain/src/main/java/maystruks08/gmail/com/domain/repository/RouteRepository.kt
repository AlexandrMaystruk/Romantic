package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Route


interface RouteRepository {

    fun getRoutes(hikeId: Long): Single<List<Route>>

    fun getRoute(routeId: Long): Single<Route>

    fun removeRoute(hikeId: Long, routeId: Long): Completable

}