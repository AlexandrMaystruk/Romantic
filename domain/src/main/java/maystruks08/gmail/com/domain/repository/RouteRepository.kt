package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.GeoPoint
import maystruks08.gmail.com.domain.entity.Route


interface RouteRepository {

    fun getRoutes(hikeId: Long): Single<List<Route>>

    fun getRoute(hikeId: Long, routeId: Long): Single<Route>

    fun addNewGeoPoint(hikeId: Long, routeId: Long,geoPoint: GeoPoint): Single<Route>

    fun removeGeoPoint(hikeId: Long, routeId: Long, geoPoint: GeoPoint): Single<Route>

    fun removeRoute(hikeId: Long, routeId: Long): Completable


}