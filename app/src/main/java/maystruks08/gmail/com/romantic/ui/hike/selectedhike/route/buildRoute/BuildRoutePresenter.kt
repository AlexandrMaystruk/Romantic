package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build.BuildRouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import org.osmdroid.util.GeoPoint
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class BuildRoutePresenter @Inject constructor(private val router: Router,
                                              private val routeInteractor: BuildRouteInteractor) : BuildRouteContract.Presenter,
    BasePresenter<BuildRouteContract.View>() {


    private val geoPoinList = mutableListOf<GeoPoint>()


    override fun buildPath(id: Long, type: RouteType, listPoint: List<Point>) {
        compositeDisposable.add(
            routeInteractor.buildRoute(id, type, listPoint)
                .subscribe(::onBuildPathSuccess, ::onBuildPathFailure)
        )
    }

    private fun onBuildPathSuccess(route: Route) {
        //todo show route
    }

    private fun onBuildPathFailure(t: Throwable) {
        t.printStackTrace()
    }

    override fun onNewPointAdded(route: Route, geoPoint: Point) {
        compositeDisposable.add(
            routeInteractor.addNewPoint(route, geoPoint)
                .subscribe(::onNewPointAddSuccess, ::onNewPointAddFailure)
        )
    }

    private fun onNewPointAddSuccess(route: Route) {
        //todo show new route
    }

    private fun onNewPointAddFailure(t: Throwable) {
        t.printStackTrace()
    }
}
