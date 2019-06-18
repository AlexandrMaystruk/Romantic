package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import org.osmdroid.util.GeoPoint
import javax.inject.Inject
import android.preference.PreferenceManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
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
import kotlinx.android.synthetic.main.fragment_route.map
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.romantic.DistanceUtil
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.views.overlay.*
import java.math.BigDecimal
import java.math.RoundingMode

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
        initMapEventListener()
    }

    private fun initCardSwipe() {
        context?.let {
            val swipeHelper = object : SwipeAndDragActionHelper(it) {

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (direction == ItemTouchHelper.LEFT) {
                        presenter.onPointRemoved(viewHolder.adapterPosition)
                    }
                }

                override fun onMoved(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                    fromPos: Int, target: RecyclerView.ViewHolder, toPos: Int, x: Int, y: Int
                ) {
                    presenter.onPointMoved(fromPos, toPos)
                    super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
                }
            }
            ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerViewGeoPoint)
        }
    }

    private fun initMapEventListener() {
        val overlayEvents = MapEventsOverlay(object : MapEventsReceiver {
            override fun longPressHelper(p: GeoPoint): Boolean {
                presenter.onPointAdd(p)
                return false
            }

            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                return false
            }
        })
        map.overlays.add(overlayEvents)
        map.invalidate()
    }

    override fun showPoints(points: List<Point>) {
        adapter.pointList = points.toMutableList()
        recyclerViewGeoPoint.scrollToPosition(adapter.pointList.lastIndex + 1)
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
        presenter.buildPath(hikeId!!, RouteType.MAIN)
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
        Polyline(map).apply {
            subDescription = Polyline::class.java.canonicalName
            width = 6f
            points = geoPointList
            isGeodesic = true
            context?.let {
                this.color = ContextCompat.getColor(it, R.color.colorGrey)
            }
            map.overlayManager.add(this)
        }
        map.invalidate()
    }

    override fun showRoutePath(geoPointList: List<GeoPoint>, @ColorRes color: Int) {
        Polyline(map).apply {
            subDescription = Polyline::class.java.canonicalName
            width = 6f
            points = geoPointList
            isGeodesic = true
            context?.let {
                this.color = ContextCompat.getColor(it, color)
            }
            map.overlayManager.add(this)
            map.invalidate()
        }
    }

    override fun showDefaultMarker(markerId: String, point: GeoPoint) {
        Marker(map).apply {
            id = markerId
            position = point
            context?.let { context ->
                ContextCompat.getDrawable(
                    context,
                    R.drawable.marker_default
                )?.let { setIcon(it) }
            }
            isDraggable = true
            map.overlays.add(this)
            setOnMarkerDragListener(object : Marker.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: Marker) {
                    presenter.onMarkerMoved(marker.id, marker.position)
                }

                override fun onMarkerDragStart(marker: Marker) {}
                override fun onMarkerDrag(marker: Marker) {}
            })
            map.invalidate()
        }
    }


    override fun showMarker(markerId: String, point: GeoPoint, @DrawableRes drawable: Int) {
        Marker(map).apply {
            id = markerId
            position = point
            isDraggable = true
            setIcon(context?.getDrawable(drawable))
            map.overlays.add(this)

            setOnMarkerDragListener(object : Marker.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: Marker) {
                    presenter.onMarkerMoved(marker.id, marker.position)
                }

                override fun onMarkerDragStart(marker: Marker) {
                }

                override fun onMarkerDrag(marker: Marker) {}
            })
            map.invalidate()
        }
    }

    override fun removeMarker(markerId: String) {
        map.overlayManager.removeAll { it is Marker && it.id == markerId }
        map.invalidate()
    }

    override fun removeMarkers() {
        map.overlayManager.removeAll { it is Marker }
        map.invalidate()
    }

    override fun removeAllPolyline() {
        map.overlayManager.removeAll { it is Polyline }
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

    private fun drawDashedPolyLine(listOfPoints: List<GeoPoint>, @ColorRes color: Int) {
        if (listOfPoints.size < 2) return
        var added = false
        listOfPoints.forEachIndexed { i, _ ->
            val distance = getConvertedDistance(listOfPoints[i], listOfPoints[i + 1])
            if (distance < 0.002) {
                added = if (!added) {
                    Polyline(map).apply {
                        subDescription = Polyline::class.java.canonicalName
                        width = 6f
                        points.add(listOfPoints[i])
                        points.add(listOfPoints[i + 1])
                        isGeodesic = true
                        context?.let {
                            this.color = ContextCompat.getColor(it, color)
                        }
                        map.overlayManager.add(this)
                    }
                    true
                } else {
                    false
                }
            } else {
                val countOfDivisions = (distance / 0.002).toInt()
                val latDiff = (listOfPoints[i + 1].latitude - listOfPoints[i].latitude) / countOfDivisions
                val longGiff = (listOfPoints[i + 1].longitude - listOfPoints[i].longitude) / countOfDivisions
                var lastKnowLatLng = GeoPoint(listOfPoints[i].latitude, listOfPoints[i].longitude)
                for (j in 0 until countOfDivisions) {
                    val nextLatLng = GeoPoint(lastKnowLatLng.latitude + latDiff, lastKnowLatLng.longitude + longGiff)
                    added = if (!added) {
                        Polyline(map).apply {
                            width = 6f
                            points.add(listOfPoints[i])
                            points.add(listOfPoints[i + 1])
                            isGeodesic = true
                            context?.let {
                                this.color = ContextCompat.getColor(it, android.R.color.transparent)
                            }
                            map.overlayManager.add(this)
                        }
                        true
                    } else {
                        false
                    }
                    lastKnowLatLng = nextLatLng
                }
            }

        }
        map.invalidate()
    }

    private fun getConvertedDistance(startPoint: GeoPoint, endPoint: GeoPoint): Double {
        val distance = DistanceUtil.distance(
            startPoint.latitude,
            startPoint.longitude,
            endPoint.latitude,
            endPoint.longitude
        )
        return BigDecimal(distance).apply { setScale(3, RoundingMode.DOWN) }.toDouble()
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