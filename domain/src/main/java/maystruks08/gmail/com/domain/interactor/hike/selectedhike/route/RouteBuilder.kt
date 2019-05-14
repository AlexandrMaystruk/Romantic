package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.GeoPoint
import maystruks08.gmail.com.domain.entity.Route

interface RouteBuilder {

    fun buildRout(listGeoPoint: List<GeoPoint>) : Route

    fun addNewPoint(route: Route): Single<Route>
}