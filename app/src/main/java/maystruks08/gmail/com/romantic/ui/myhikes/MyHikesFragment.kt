package maystruks08.gmail.com.romantic.ui.myhikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_hikes.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class MyHikesFragment : Fragment(), MyHikesContract.View {

    @Inject
    lateinit var presenter: MyHikesContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var hikeAdapter: HikeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        App.myHikeComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_my_hikes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(true, "My Hikes"),
            activity as ConfigToolbar
        )
    }

    private fun init() {
        presenter.bindView(this)
        presenter.initFragmentByUser()
        hikeAdapter = HikeAdapter { hikeItemClicked(it) }
        hikesRecyclerView.layoutManager = LinearLayoutManager(context)
        hikesRecyclerView.adapter = hikeAdapter

        rootFab.setOnClickListener {
            presenter.onCreateHikeClicked()
        }
    }

    override fun showHikes(hikeList: List<Hike>) {
        hikeAdapter.hikeList = hikeList
    }

    private fun hikeItemClicked(hike: Hike) {
        presenter.onHikeClicked(hike)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.end()
        App.clearMeHikesComponent()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }


    companion object {
        fun getInstance(): MyHikesFragment = MyHikesFragment()
    }
}