package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import android.graphics.drawable.Drawable
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.util.ArrayList


interface RouteContract {

    interface View : IView {

        fun showRoute(geoPointList: ArrayList<GeoPoint>)

        fun showMarker(point: GeoPoint, drawable: Drawable)

        fun removeMarker(marker: Marker)

    }

    interface Presenter : IPresenter<View> {

        fun onNewPointAdded(hikeId: Long, routeId: Long, geoPoint: GeoPoint)

        fun buildPath(geoPointList: ArrayList<GeoPoint>)
    }
}
