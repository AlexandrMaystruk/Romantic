package maystruks08.gmail.com.romantic.ui.main.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_root_tab.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class RootTabFragment : Fragment(), HikeListContract.View,
    HikeListListener {

    @Inject
    lateinit var presenter: HikeListContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    lateinit var fragmentAdapter: PagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        App.hikeComponent?.inject(this)
        presenter.bindView(this)
        return inflater.inflate(R.layout.fragment_root_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(visible = true, title = "All Hikes", menu = R.menu.main_menu),
            activity as ConfigToolbar
        )
    }

    private fun init() {
        fragmentAdapter = PagerAdapter(childFragmentManager, presenter)
        rootTabs.setupWithViewPager(rootViewPager)
        rootViewPager.setPageTransformer(true, ZoomOutPageTransformer())
        rootViewPager.adapter = fragmentAdapter
        presenter.loadHikeData()

    }


    override fun showHikes(hikeList: List<Hike>, position: Int) {
        fragmentAdapter.fragmentList[position].hikeAdapter?.hikeList = hikeList
    }

    override fun onHikeClicked(hike: Hike) {
        presenter.onHikeClicked(hike)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.end()
        App.clearHikeListComponent()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }


    companion object {
        fun getInstance(): RootTabFragment =
            RootTabFragment()
    }
}