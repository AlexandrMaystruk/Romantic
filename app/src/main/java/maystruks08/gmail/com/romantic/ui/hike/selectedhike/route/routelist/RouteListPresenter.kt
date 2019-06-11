package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import org.osmdroid.util.GeoPoint
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class RouteListPresenter @Inject constructor(private val router: Router,
    private val routeInteractor: RouteInteractor) : RouteListContract.Presenter,
    BasePresenter<RouteListContract.View>() {


    override fun getHikeRoutes(hikeId: Long) {
       compositeDisposable.add(
           routeInteractor.getHikeRoutes(hikeId)
               .subscribe(::onGetHikeRouteSuccess,::onGetHikeRouteFailure))

    }

    private fun onGetHikeRouteSuccess(routes: List<Route>){
        view?.showRoutes(routes)
    }

    private fun onGetHikeRouteFailure(t: Throwable){
        t.printStackTrace()
    }

    override fun onHikeRouteClick(route: Route) {
        router.navigateTo(Screens.RouteScreen(route))
    }

    override fun onBuildRouteClicked(hikeId: Long) {
        router.navigateTo(Screens.BuildRouteScreen(hikeId))
    }
}
