package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.GeoPoint
import maystruks08.gmail.com.domain.entity.Route

interface RouteInteractor {

    fun getHikeRoutes(hikeId: Long): Single<List<Route>>

    fun buildRout()

    fun addNewPoint(hikeId: Long, routeId: Long, geoPoint: GeoPoint): Single<Route>
}