package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import org.osmdroid.util.GeoPoint
import java.util.ArrayList

import javax.inject.Inject


class RoutePresenter @Inject constructor(private val routeInteractor: RouteInteractor) : RouteContract.Presenter,
    BasePresenter<RouteContract.View>() {


    private val geoPoinList = mutableListOf<GeoPoint>()

    override fun buildPath(geoPointList: ArrayList<GeoPoint>) {
    }

    override fun onNewPointAdded(hikeId: Long, routeId: Long, geoPoint: GeoPoint) {
        compositeDisposable.add(
            routeInteractor.addNewPoint(hikeId, routeId, fromOsmGeoPoint(geoPoint)).subscribe()
        )
    }

    //todo move logic to viewMapper
    private fun fromOsmGeoPoint(geoPoint: GeoPoint): maystruks08.gmail.com.domain.entity.GeoPoint {
        return geoPoint.let {
            maystruks08.gmail.com.domain.entity.GeoPoint(it.latitude, it.longitude)
        }
    }

}
