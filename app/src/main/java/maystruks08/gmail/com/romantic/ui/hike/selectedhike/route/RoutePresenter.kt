package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.toGeoPoint
import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class RoutePresenter @Inject constructor(private val router: Router) : RouteContract.Presenter,
    BasePresenter<RouteContract.View>() {


    override fun initRoutePath(routeViewModel: RouteViewModel?) {
        routeViewModel?.let {
            renderRoutePath(it)
        }
    }

    private fun renderRoutePath(route: RouteViewModel) {
        //draw path
        route.completeRoutePath?.let { list ->
            view?.showRoutePath(list.map { it.toGeoPoint() }, R.color.colorGreen)
            view?.changeCameraFocus(list.first().toGeoPoint(), 14.0)
        }
        //add markers
        var markerRes: Int
        route.points.forEachIndexed { index, point ->
            markerRes = when (index) {
                0 -> R.drawable.marker_flag_start
                route.points.lastIndex -> R.drawable.marker_flag_finish
                else -> R.drawable.marker_blue
            }
            view?.showMarker(point.id, point.toGeoPoint(), markerRes)
        }
    }
}
