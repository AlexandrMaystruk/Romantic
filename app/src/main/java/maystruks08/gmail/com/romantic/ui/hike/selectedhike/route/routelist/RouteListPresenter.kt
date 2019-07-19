package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class RouteListPresenter @Inject constructor(
    private val router: Router,
    private val routeInteractor: RouteInteractor
) : RouteListContract.Presenter,
    BasePresenter<RouteListContract.View>() {

    override fun getHikeRoutes(hikeId: Long) {
        compositeDisposable.add(
            routeInteractor.getHikeRoutes(hikeId)
                .subscribe(::onGetHikeRouteSuccess, ::onGetHikeRouteFailure)
        )
    }

    private fun onGetHikeRouteSuccess(routes: List<Route>) {
        view?.showRoutes(routes)
    }

    private fun onGetHikeRouteFailure(t: Throwable) {
        t.printStackTrace()
    }

    override fun onHikeRouteClick(route: Route) {
        router.navigateTo(Screens.RouteScreen(route))
    }

    override fun onDeleteRoute(position: Int, route: Route) {
        view?.showRouteRemoved(position)
        compositeDisposable.add(
            routeInteractor.removeRoute(route)
                .subscribe(::onRemoveRouteSuccess, ::onRemoveRouteFailure)
        )
    }

    private fun onRemoveRouteSuccess() {
        view?.hideLoading()
    }

    private fun onRemoveRouteFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    override fun onChangeRoute(position: Int, route: Route) {
        router.navigateTo(Screens.ChangeRouteScreen(route))
    }

    override fun onCreateRouteClicked() {
        view?.showSelectRoteTypeDialog()
    }

    override fun onRouteTypeSelected(hikeId: Long, name: String, type: RouteType) {
        router.navigateTo(Screens.BuildRouteScreen(hikeId, name, type))
    }
}
