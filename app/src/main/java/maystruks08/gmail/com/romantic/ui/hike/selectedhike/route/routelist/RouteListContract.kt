package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView

interface RouteListContract {

    interface View : IView {

        fun showRoutes(routes: List<Route>)

        fun showSelectRoteTypeDialog()

        fun showRouteRemoved(position: Int)

    }

    interface Presenter : IPresenter<View> {

        fun onDeleteRoute(position: Int, route: Route)

        fun onChangeRoute(position: Int, route: Route)

        fun getHikeRoutes(hikeId: Long)

        fun onHikeRouteClick(route: Route)

        fun onCreateRouteClicked()

        fun onRouteTypeSelected(hikeId: Long, name: String, type: RouteType)
    }
}
