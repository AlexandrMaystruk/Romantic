package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import javax.inject.Inject
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_create_route_header.*
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import maystruks08.gmail.com.romantic.ui.viewmodel.RouteViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_build_route.*
import kotlinx.android.synthetic.main.fragment_route.map
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.getDisplayHeight
import maystruks08.gmail.com.romantic.getDisplayWidth


class BuildRouteFragment : Fragment(), BuildRouteContract.View {


    @Inject
    lateinit var presenter: BuildRouteContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter: PointAdapter

    private var hikeId: Long? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.buildRouteComponent?.inject(this)
        presenter.bindView(this)
        hikeId = arguments?.getLong(BUILD_HIKE_ROUTE)

        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
        return inflater.inflate(R.layout.fragment_build_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(false)
        map.setMultiTouchControls(true)
        map.controller.setZoom(9.5)
    }


    private fun initView() {
        adapter = PointAdapter({ itemClicked(it) }, { itemButtonClicked() })
        recyclerViewGeoPoint.layoutManager = LinearLayoutManager(context)
        recyclerViewGeoPoint.adapter = adapter
        initCardSwipe()

        fabBuildRoute.setOnClickListener {
            presenter.buildPath(hikeId!!, RouteType.MAIN)
        }
    }

    private fun initCardSwipe() {
        context?.let {
            val swipeHelper = object : SwipeAndDragActionHelper(it) {

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (direction == ItemTouchHelper.LEFT) {
                        presenter.onPointRemoved(viewHolder.adapterPosition)
                    }
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    presenter.onPointMoved(viewHolder.adapterPosition, target.adapterPosition)
                    return true
                }
            }
            ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerViewGeoPoint)
        }
    }


    override fun showPointInList(point: Point) {
        adapter.addPoint(point)
        recyclerViewGeoPoint.scrollToPosition(adapter.pointList.indexOf(point))
    }

    override fun showPointRemoved(position: Int) {
        adapter.onItemRemove(position)
    }

    override fun showPointMoved(fromPosition: Int, toPosition: Int) {
        adapter.onItemMove(fromPosition, toPosition)
    }

    private fun itemClicked(point: Point) {
    }

    private fun itemButtonClicked() {
        context?.let {
            val centerGeoPoint = map.projection.fromPixels(it.getDisplayHeight() / 2, it.getDisplayWidth() / 2)
            presenter.onStartPointAdded(GeoPoint(centerGeoPoint.latitude, centerGeoPoint.longitude))
        }
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("Create Route")
                .navigationIcon(R.drawable.ic_arrow_back_white_24dp)
                .bottomBarVisibility(false)
                .build(),
            activity as ConfigToolbar
        )
    }

    override fun showRoute(geoPointList: List<GeoPoint>) {
        val line = Polyline(map)
        line.subDescription = Polyline::class.java.canonicalName
        line.width = 6f
        line.points = geoPointList
        line.isGeodesic = true
        context?.let { line.color = ContextCompat.getColor(it, R.color.colorPrimaryDark) }
        map.overlayManager.add(line)
        map.invalidate()
    }

    override fun showMarker(point: GeoPoint) {
        val marker = Marker(map)
        marker.position = point
        context?.let { context ->
            ContextCompat.getDrawable(context, R.drawable.marker_default)?.let { marker.setIcon(it) }
        }
        marker.isDraggable = true
        map.overlays.add(marker)
        marker.setOnMarkerClickListener { _, _ ->
            true
        }

        marker.setOnMarkerDragListener(object : Marker.OnMarkerDragListener {
            override fun onMarkerDragEnd(marker: Marker?) {
                marker?.position?.let {
                    presenter.onMarkerMoved(it)
                }
            }

            override fun onMarkerDragStart(marker: Marker?) {
                marker?.position?.let {
                    presenter.onMarkerMoved(it)
                }
            }

            override fun onMarkerDrag(marker: Marker?) {
            }
        })
        map.invalidate()

    }

    override fun showMarker(point: GeoPoint, drawable: Drawable) {
        val marker = Marker(map)
        marker.position = point
        marker.setIcon(drawable)
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
        map.invalidate()

    }

    override fun removeMarker(marker: Marker) {
        map.overlayManager.remove(marker)
        map.invalidate()
    }

    override fun clearOverlays() {
        map.overlayManager.clear()
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

        fun getInstance(hikeId: Long): BuildRouteFragment =
            BuildRouteFragment().apply {
                arguments = Bundle().apply {
                    putLong(BUILD_HIKE_ROUTE, hikeId)
                }
            }

        fun getInstance(): BuildRouteFragment = BuildRouteFragment()

        fun getInstance(route: RouteViewModel): BuildRouteFragment =
            BuildRouteFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUILD_HIKE_ROUTE, route)
                }
            }
    }
}