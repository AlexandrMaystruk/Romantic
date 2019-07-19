package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_route.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import javax.inject.Inject
import android.preference.PreferenceManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor

import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController

class RouteFragment : Fragment(), RouteContract.View {

    @Inject
    lateinit var presenter: RouteContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private var routeViewModel: RouteViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.routeComponent?.inject(this)
        routeViewModel = arguments?.getParcelable(HIKE_ROUTE)
        presenter.bindView(this)
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        return inflater.inflate(R.layout.fragment_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        map.setMultiTouchControls(true)

        presenter.initRoutePath(routeViewModel)
    }


    override fun changeCameraFocus(geoPoint: GeoPoint, zoom: Double) {
        map.controller.apply {
            setZoom(zoom)
            setCenter(geoPoint)
        }
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder().visibility(true)
                .title("Route")
                .navigationIcon(R.drawable.ic_arrow_back_white_24dp)
                .bottomBarVisibility(false)
                .build(),
            activity as ConfigToolbar
        )
    }

    override fun showRoutePath(geoPointList: List<GeoPoint>, @ColorRes color: Int) {
        Polyline(map).apply {
            subDescription = Polyline::class.java.canonicalName
            width = 6f
            setPoints(geoPointList)
            isGeodesic = true
            context?.let {
                this.color = ContextCompat.getColor(it, color)
            }
            map.overlayManager.add(this)
            map.invalidate()
        }
    }

    override fun showMarker(markerId: String, point: GeoPoint, @DrawableRes drawable: Int) {
        Marker(map).apply {
            id = markerId
            position = point
            isDraggable = true
            icon = context?.getDrawable(drawable)
            map.overlays.add(this)
        }
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

    companion object {

        private const val HIKE_ROUTE = "hikeRoute"

        fun getInstance(route: RouteViewModel): RouteFragment =
            RouteFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HIKE_ROUTE, route)
                }
            }
    }
}