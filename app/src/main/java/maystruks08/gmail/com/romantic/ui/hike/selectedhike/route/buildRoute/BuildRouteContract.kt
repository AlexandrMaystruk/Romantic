package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import android.graphics.drawable.Drawable
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.util.ArrayList


interface BuildRouteContract {

    interface View : IView {

        fun showRoute(geoPointList: List<GeoPoint>)

        fun showMarker(point: GeoPoint, drawable: Drawable)

        fun removeMarker(marker: Marker)

    }

    interface Presenter : IPresenter<View> {

        fun buildPath(id: Long, type: RouteType, listPoint: List<Point>)

        fun onNewPointAdded(route: Route, geoPoint: Point)

    }
}
