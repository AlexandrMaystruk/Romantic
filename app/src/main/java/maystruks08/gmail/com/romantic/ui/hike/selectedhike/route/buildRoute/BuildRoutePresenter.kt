package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build.BuildRouteInteractor
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.toGeoPoint
import maystruks08.gmail.com.romantic.toPoint
import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import org.osmdroid.util.GeoPoint
import java.util.*

import javax.inject.Inject


class BuildRoutePresenter @Inject constructor(
    private val routeInteractor: BuildRouteInteractor
) : BuildRouteContract.Presenter,
    BasePresenter<BuildRouteContract.View>() {

    private val pointsOnMap = mutableListOf<Point>()
    private var pointCount = 0

    override fun onShowHeaderClicked() {
        view?.showHeader()
    }

    override fun buildPath(hikeId: Long, name: String, type: RouteType) {
        view?.enableInputButton(false)
        compositeDisposable.add(
            routeInteractor.buildRoute(hikeId, name, type, pointsOnMap)
                .subscribe(::renderRoutePath, ::onBuildPathFailure)
        )
    }

    override fun onNewPointAdded(route: Route, geoPoint: Point) {
        compositeDisposable.add(
            routeInteractor.addNewPoint(route, geoPoint)
                .subscribe(::onNewPointAddSuccess, ::onNewPointAddFailure)
        )
    }

    override fun onPointAdd(geoPoint: GeoPoint) {
        pointsOnMap.add(geoPoint.toPoint(number = ++pointCount))
        view?.showPoints(pointsOnMap)
        renderRouteScheme()
    }

    override fun onPointMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(pointsOnMap, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(pointsOnMap, i, i - 1)
            }
        }
        renderRouteScheme()
        view?.showPointMoved(fromPosition, toPosition)
    }

    override fun onMarkerMoved(markerId: String, geoPoint: GeoPoint) {
        pointsOnMap.firstOrNull { it.id == markerId }?.let {
            pointsOnMap[pointsOnMap.indexOf(it)] = it.apply {
                lat = geoPoint.latitude
                lon = geoPoint.longitude
            }
            view?.removeAllPolyline()
            view?.showRoute(pointsOnMap.map { toGeoPoint(it) })
        }
    }

    override fun onPointRemoved(position: Int) {
        pointCount--
        pointsOnMap.removeAt(position)
        view?.showPointRemoved(position)
        renderRouteScheme()
    }

    private fun renderRouteScheme(isShowInPointAdapter: Boolean = false) {
        val geoPoints = pointsOnMap.map { it.toGeoPoint() }
        view?.removeMarkers()
        view?.removeAllPolyline()
        view?.showRoute(geoPoints)
        if (isShowInPointAdapter) {
            view?.showPoints(pointsOnMap)
        }

        geoPoints.forEachIndexed { index, gp ->
            pointsOnMap[index].number = index + 1
            val p = pointsOnMap[index]
            val markerRes = when (p.number) {
                1 -> R.drawable.marker_flag_start
                geoPoints.lastIndex + 1 -> R.drawable.marker_flag_finish
                else -> R.drawable.marker_blue
            }
            view?.showMarker(p.id, gp, markerRes)
        }
    }

    private fun renderRoutePath(route: Route) {
        view?.enableInputButton(true)
        view?.buildRouteSuccess()
        //remove all overlays object
        view?.removeMarkers()
        view?.removeAllPolyline()
        //draw path
        route.completeRoutePath?.let { list ->
            view?.showRoute(list.map { toGeoPoint(it) })
        }
        //add markers
        pointsOnMap.clear()
        pointsOnMap.addAll(route.points)
        var markerRes: Int
        route.points.forEachIndexed { index, point ->
            markerRes = when (index) {
                0 -> R.drawable.marker_flag_start
                route.points.lastIndex -> R.drawable.marker_flag_finish
                else -> R.drawable.marker_blue
            }
            view?.showMarker(point.id, toGeoPoint(point), markerRes)
        }
    }

    private fun onNewPointAddSuccess(route: Route) {
    }

    private fun onBuildPathFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNewPointAddFailure(t: Throwable) {
        t.printStackTrace()
    }

    override fun onRouteNeedToChange(route: RouteViewModel) {
        pointsOnMap.addAll(route.points)
        renderRouteScheme(true)
        view?.changeCameraFocus(route.points.first().toGeoPoint(), 14.0)
    }

    private fun toGeoPoint(point: Point): GeoPoint {
        return point.let { GeoPoint(it.lat, it.lon) }
    }
}
