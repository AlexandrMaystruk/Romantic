package maystruks08.gmail.com.romantic.ui.hike.myhikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_my_hikes.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.toast
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
        initCardSwipe()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("My Hikes")
                .build(),
            activity as ConfigToolbar
        )
    }

    private fun init() {
        presenter.bindView(this)
        presenter.initFragmentByUser()
        setAdapter()

        rootFab.setOnClickListener {
            presenter.onCreateHikeClicked()
        }
    }


    private fun setAdapter(){
        hikeAdapter = HikeAdapter { hikeItemClicked(it) }
        hikesRecyclerView.layoutManager = LinearLayoutManager(hikesRecyclerView.context)
        hikesRecyclerView.adapter = hikeAdapter
    }

    private fun initCardSwipe() {
        context?.let {
            val swipeHelper = object : SwipeActionHikeHelper(it) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.LEFT) {
                        presenter.onDeleteHikeClicked(position, hikeAdapter.hikeList[position])
                    }
                    if (direction == ItemTouchHelper.RIGHT) {
                        presenter.onLeaveFromHikeClicked(position, hikeAdapter.hikeList[position])
                    }
                }
            }
            ItemTouchHelper(swipeHelper).attachToRecyclerView(hikesRecyclerView)
        }
    }

    override fun showHikes(hikeList: List<Hike>) {
        hikeAdapter.hikeList = hikeList.toMutableList()
    }

    override fun showHikeRemoved(position: Int) {
        hikeAdapter.removeItem(position)
    }

    override fun updateItem(position: Int) {
        hikeAdapter.notifyItemChanged(position)
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
        context?.toast(t.message ?: t.localizedMessage)
    }


    companion object {
        fun getInstance(): MyHikesFragment =
            MyHikesFragment()
    }
}