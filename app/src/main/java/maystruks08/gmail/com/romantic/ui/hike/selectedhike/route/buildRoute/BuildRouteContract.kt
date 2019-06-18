package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView
import org.osmdroid.util.GeoPoint

interface BuildRouteContract {

    interface View : IView {


        fun showRoute(geoPointList: List<GeoPoint>)

        fun showRoutePath(geoPointList: List<GeoPoint>, @ColorRes color: Int)

        fun showDefaultMarker(markerId: String, point: GeoPoint)

        fun showMarker(markerId: String, point: GeoPoint, @DrawableRes drawable: Int)

        fun removeMarker(markerId: String)

        fun removeMarkers()

        fun removeAllPolyline()

        fun showPointMoved(fromPosition: Int, toPosition: Int)

        fun showPointRemoved(position: Int)

        fun showPoints(points: List<Point>)

    }

    interface Presenter : IPresenter<View> {

        fun buildPath(id: Long, type: RouteType)

        fun onPointAdd(geoPoint: GeoPoint)

        fun onNewPointAdded(route: Route, geoPoint: Point)

        fun onPointMoved(fromPosition: Int, toPosition: Int)

        fun onPointRemoved(position: Int)

        fun onMarkerMoved(markerId: String, geoPoint: GeoPoint)

    }
}
