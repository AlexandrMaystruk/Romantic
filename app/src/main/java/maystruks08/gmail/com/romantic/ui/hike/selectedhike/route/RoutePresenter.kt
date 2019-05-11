package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import org.osmdroid.util.GeoPoint
import java.util.ArrayList

import javax.inject.Inject


class RoutePresenter @Inject constructor(private val routeInteractor: RouteInteractor)
    : RouteContract.Presenter, BasePresenter<RouteContract.View>() {


    override fun buildPath(geoPointList: ArrayList<GeoPoint>) {

    }

}
