package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_route.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow
import java.util.ArrayList
import javax.inject.Inject
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_create_route_header.*
import maystruks08.gmail.com.romantic.R

import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class BuildRouteFragment : Fragment(), BuildRouteContract.View {


    @Inject
    lateinit var presenter: BuildRouteContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter: GeoPointAdapter


    private var line: Polyline? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.buildRouteComponent?.inject(this)
        presenter.bindView(this)
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        return inflater.inflate(R.layout.fragment_build_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(false)
        map.setMultiTouchControls(true)


        val mapController = map.controller
        mapController.setZoom(9.5)

        val startPoint = GeoPoint(48.8583, 2.2944)
        mapController.setCenter(startPoint)

    }


    private fun initView() {
        adapter = GeoPointAdapter({ itemClicked(it) }, { itemButtonClicked() })
        recyclerViewGeoPoint.layoutManager = LinearLayoutManager(context)
        recyclerViewGeoPoint.adapter = adapter
    }

    private fun itemClicked(point: maystruks08.gmail.com.domain.entity.Point) {
    }

    private fun itemButtonClicked() {
        //todo add new point on map
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(
                true,
                "Create Route",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ),
            activity as ConfigToolbar
        )
    }

    override fun showRoute(geoPointList: List<GeoPoint>) {
        if (null != line) {
            map.overlayManager.remove(line)
        }
        line = Polyline(map)
        line?.subDescription = Polyline::class.java.canonicalName
        line?.width = 10f
        line?.points = geoPointList
        line?.isGeodesic = true
        line?.infoWindow = BasicInfoWindow(R.layout.bonuspack_bubble, map)
        map.overlayManager.add(line)
        map.invalidate()
    }


    override fun showMarker(point: GeoPoint, drawable: Drawable) {
        val marker = Marker(map)
        marker.position = point
        marker.setIcon(drawable)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.isDraggable = true
        map.overlays.add(marker)
        marker.setOnMarkerClickListener { _, _ ->
            true
        }

        marker.setOnMarkerDragListener(object : Marker.OnMarkerDragListener {
            override fun onMarkerDragEnd(marker: Marker?) {
            }

            override fun onMarkerDragStart(marker: Marker?) {
            }

            override fun onMarkerDrag(marker: Marker?) {
            }
        })
    }

    override fun removeMarker(marker: Marker) {
        map.overlayManager.remove(marker)
        map.invalidate()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    override fun onDetach() {
        super.onDetach()
        App.clearCreateRouteComponent()
    }

    companion object {

        private const val BUILD_HIKE_ROUTE = "buildHikeRoute"

        fun getInstance(route: RouteViewModel): BuildRouteFragment =
            BuildRouteFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUILD_HIKE_ROUTE, route)
                }
            }
    }
}