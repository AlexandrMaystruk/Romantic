package maystruks08.gmail.com.romantic.ui.hikes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.fragment_hike_list.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class HikeListFragment : Fragment(), HikeListContract.View {

    @Inject
    lateinit var presenter: HikeListContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var hikeAdapter: HikeAdapter

    private var typeHike: TypeHike? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.hikeComponent?.inject(this)
        Log.e("LIFECYCLE", "HikeListFragment onCreate"+ typeHike?.name)
        val type = arguments?.getInt(TYPE_HIKE)
        typeHike = if (type != null) TypeHike.fromValue(type) else null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.e("LIFECYCLE", "HikeListFragment onCreateView" + typeHike?.name)
        return inflater.inflate(R.layout.fragment_hike_list, container, false)
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(true, "Hike", navigationIcon = R.drawable.ic_arrow_back_white_24dp),
            activity as ConfigToolbar
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        init()
        Log.e("LIFECYCLE", "HikeListFragment onViewCreated" + typeHike?.name)
    }

    override fun onResume() {
        super.onResume()
        Log.e("LIFECYCLE", "HikeListFragment onResume" + typeHike?.name)
    }

    private fun init() {
        hikeAdapter = HikeAdapter { hikeItemClicked(it) }
        hikesRecyclerView.layoutManager = LinearLayoutManager(context)
        hikesRecyclerView.adapter = hikeAdapter
        rootFab?.setOnClickListener { presenter.onCreateHikeClicked() }

        presenter.initUI(typeHike)

    }

    private fun hikeItemClicked(hike: Hike) {
        presenter.onHikeClicked(hike)
    }

    override fun showHikes(hikeList: List<Hike>) {
        hikeAdapter.hikeList = hikeList
        Log.e("LIFECYCLE", "HikeListFragment $hikeList")
        view?.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("LIFECYCLE", "HikeListFragment onDestroyView" + typeHike?.name)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.end()
        App.clearHikeListComponent()
        Log.e("LIFECYCLE", "HikeListFragment onDestroy" + typeHike?.name)

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        private const val TYPE_HIKE = "typeHike"

        fun getInstance(typeHike: TypeHike? = null): HikeListFragment =
            HikeListFragment().apply {
                if (typeHike?.type != null) {
                    arguments = Bundle().apply {
                        putInt(TYPE_HIKE, typeHike.type)
                    }
                }
            }
    }
}
