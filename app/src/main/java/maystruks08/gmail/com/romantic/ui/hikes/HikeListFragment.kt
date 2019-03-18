package maystruks08.gmail.com.romantic.ui.hikes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.hikeComponent?.inject(this)
        presenter.bindView(this)

        val type = arguments?.getInt(TYPE_HIKE)
        typeHike = if (type != null) TypeHike.fromValue(type) else null

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

        hikeAdapter = HikeAdapter { position: Hike ->
            hikeItemClicked(position)
        }
        hikesRecyclerView.layoutManager = LinearLayoutManager(context)
        hikesRecyclerView.itemAnimator = DefaultItemAnimator()
        hikesRecyclerView.adapter = hikeAdapter

        init()
    }

    private fun init() {

        presenter.initUI(typeHike)

        fabCreateNewHike.setOnClickListener {
            presenter.onCreateHikeClicked()
        }
    }

    private fun hikeItemClicked(hike: Hike) {
        presenter.onHikeClicked(hike)
    }

    override fun showHikes(hikeList: List<Hike>) {
        hikeAdapter.hikeList = hikeList
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
                arguments = Bundle().apply {
                    if (typeHike?.type != null)
                        putInt(TYPE_HIKE, typeHike.type)
                }
            }
    }
}
