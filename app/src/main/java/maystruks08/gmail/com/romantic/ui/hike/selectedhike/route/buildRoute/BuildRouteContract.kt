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

        fun showMarker(point: GeoPoint)

        fun showMarker(point: GeoPoint, drawable: Drawable)

        fun removeMarker(marker: Marker)

        fun clearOverlays()

        fun showPointInList(point: Point)

        fun showPointMoved(fromPosition: Int, toPosition: Int)

        fun showPointRemoved(position: Int)

    }

    interface Presenter : IPresenter<View> {

        fun buildPath(id: Long, type: RouteType)

        fun onStartPointAdded(geoPoint: GeoPoint)

        fun onNewPointAdded(route: Route, geoPoint: Point)

        fun onPointMoved(fromPosition: Int, toPosition: Int)

        fun onPointRemoved(position: Int)

        fun onMarkerMoveStart(geoPoint: GeoPoint)

        fun onMarkerMoved(geoPoint: GeoPoint)

    }
}
