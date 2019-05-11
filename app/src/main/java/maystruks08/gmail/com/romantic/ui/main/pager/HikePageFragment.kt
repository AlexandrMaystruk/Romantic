package maystruks08.gmail.com.romantic.ui.main.pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hike_list.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.hike.myhikes.HikeAdapter

class HikePageFragment : Fragment() {

    var hikeAdapter: HikeAdapter? = null

    private var hikeListListener: HikeListListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_hike_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        hikeAdapter = HikeAdapter { hikeItemClicked(it) }
        hikesRecyclerView.layoutManager = LinearLayoutManager(context)
        hikesRecyclerView.adapter = hikeAdapter
    }

    private fun hikeItemClicked(hike: Hike) {
        hikeListListener?.onHikeClicked(hike)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is HikeListListener) {
            hikeListListener = parentFragment as HikeListListener
        }
    }

    companion object {

        private const val TYPE_HIKE = "typeHike"

        fun getInstance(typeHike: TypeHike? = null): HikePageFragment =
            HikePageFragment().apply {
                if (typeHike?.type != null) {
                    arguments = Bundle().apply {
                        putInt(TYPE_HIKE, typeHike.type)
                    }
                }
            }
    }
}
