package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import android.graphics.drawable.Drawable
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.util.ArrayList


interface RouteContract {

    interface View : IView {

        fun showRoute(geoPointList: List<GeoPoint>)

        fun showMarker(point: GeoPoint, drawable: Drawable)

        fun removeMarker(marker: Marker)

    }

    interface Presenter : IPresenter<View> {

        fun initView(hikeId: Long)

        fun onCreateNewRouteClich(hikeId: Long)
    }
}
