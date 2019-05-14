package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface RouteListContract {

    interface View : IView {

        fun showRoutes(routes: List<Route>)

    }

    interface Presenter : IPresenter<View> {

        fun getHikeRoutes(hikeId: Long)

        fun onHikeRouteClick(route: Route)
    }
}
