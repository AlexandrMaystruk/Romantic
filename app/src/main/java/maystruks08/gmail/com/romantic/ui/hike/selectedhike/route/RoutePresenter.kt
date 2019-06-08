package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter

import javax.inject.Inject


class RoutePresenter @Inject constructor(private val routeInteractor: RouteInteractor) : RouteContract.Presenter,
    BasePresenter<RouteContract.View>() {

    override fun initView(hikeId: Long) {
        compositeDisposable.add(
            routeInteractor.getHikeRoutes(hikeId)
                .subscribe(::onGetHikeRoutesSuccess, ::onGetHikeRoutesFailure)
        )
    }

    private fun onGetHikeRoutesSuccess(routes: List<Route>) {
        //todo show list routes
    }

    private fun onGetHikeRoutesFailure(t: Throwable) {
        t.printStackTrace()
    }

    override fun onCreateNewRouteClich(hikeId: Long) {

    }


}
