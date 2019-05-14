package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_route_list.*
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.romantic.R


class RouteListFragment : Fragment(), RouteListContract.View {

    @Inject
    lateinit var presenter: RouteListContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter: RouteAdapter

    private var hikeId: Long? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.routeComponent?.inject(this)
        presenter.bindView(this)
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hikeId = arguments?.getLong(ROUTE_HIKE_ID)
        initViews()
    }

    private fun initViews() {
        presenter.getHikeRoutes(hikeId!!)
        adapter = RouteAdapter { routeItemClicked(it) }
        routesRecyclerView.layoutManager = LinearLayoutManager(context)
        routesRecyclerView.adapter = adapter
    }


    private fun routeItemClicked(route: Route) {
        presenter.onHikeRouteClick(route)
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(
                true,
                "Routes",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ),
            activity as ConfigToolbar
        )
    }

    override fun showRoutes(routes: List<Route>) {
        adapter.routeList = routes
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        private const val ROUTE_HIKE_ID = "routeHikeId"

        fun getInstance(hikeId: Long): RouteListFragment =
            RouteListFragment().apply {
                arguments = Bundle().apply {
                    putLong(ROUTE_HIKE_ID, hikeId)
                }
            }
    }
}