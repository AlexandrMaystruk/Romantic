package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView
import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import org.osmdroid.util.GeoPoint


interface RouteContract {

    interface View : IView {

        fun showRoutePath(geoPointList: List<GeoPoint>, @ColorRes color: Int)

        fun showMarker(markerId: String, point: GeoPoint, @DrawableRes drawable: Int)

        fun changeCameraFocus(geoPoint: GeoPoint, zoom: Double)

    }

    interface Presenter : IPresenter<View> {

        fun initRoutePath(routeViewModel: RouteViewModel?)

    }
}
