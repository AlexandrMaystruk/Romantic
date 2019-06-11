package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build.BuildRouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import org.osmdroid.util.GeoPoint
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class BuildRoutePresenter @Inject constructor(
    private val router: Router,
    private val routeInteractor: BuildRouteInteractor
) : BuildRouteContract.Presenter,
    BasePresenter<BuildRouteContract.View>() {

    private val geoPointList = mutableListOf<Point>()
    private var movedMarkerPoint: Point? = null

    override fun onStartPointAdded(geoPoint: GeoPoint) {
        geoPointList.add(Point(geoPoint.latitude, geoPoint.longitude))
        view?.showPointInList(Point(geoPoint.longitude, geoPoint.latitude))
        view?.showMarker(geoPoint)
        view?.showRoute(geoPointList.map { GeoPoint(it.lat, it.lon) })
    }

    override fun buildPath(id: Long, type: RouteType) {
        compositeDisposable.add(
            routeInteractor.buildRoute(id, type, geoPointList)
                .subscribe(::onBuildPathSuccess, ::onBuildPathFailure)
        )
    }

    override fun onNewPointAdded(route: Route, geoPoint: Point) {
        compositeDisposable.add(
            routeInteractor.addNewPoint(route, geoPoint)
                .subscribe(::onNewPointAddSuccess, ::onNewPointAddFailure)
        )
    }

    override fun onPointMoved(fromPosition: Int, toPosition: Int) {
        val prev = geoPointList.removeAt(fromPosition)
        geoPointList.add(
            if (toPosition > fromPosition) {
                toPosition - 1
            } else {
                toPosition
            }, prev
        )

        view?.showPointMoved(fromPosition, toPosition)
        //todo rebuild path
    }

    override fun onMarkerMoveStart(geoPoint: GeoPoint) {
        movedMarkerPoint = Point(geoPoint.latitude, geoPoint.longitude)
    }

    override fun onMarkerMoved(geoPoint: GeoPoint) {

    }

    override fun onPointRemoved(position: Int) {
        geoPointList.removeAt(position)
        view?.showPointRemoved(position)
        //todo rebuild path
    }


    private fun onBuildPathSuccess(route: Route) {
       //remove all overlays object
        view?.clearOverlays()
       //draw path
        route.completeRoutePath?.let { list ->
            view?.showRoute(list.map { GeoPoint(it.lat, it.lon) })
        }
        //add markers
        route.points.forEach {
            view?.showMarker(GeoPoint(it.lat, it.lon))
        }
    }

    private fun onBuildPathFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNewPointAddSuccess(route: Route) {
        //todo show new route
    }

    private fun onNewPointAddFailure(t: Throwable) {
        t.printStackTrace()
    }
}
