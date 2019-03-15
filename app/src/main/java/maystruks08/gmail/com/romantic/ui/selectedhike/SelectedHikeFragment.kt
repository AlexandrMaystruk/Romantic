package maystruks08.gmail.com.romantic.ui.selectedhike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_hike.*
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import javax.inject.Inject

class SelectedHikeFragment : Fragment(), SelectedHikeContract.View {

    @Inject
    lateinit var presenter: SelectedHikeContract.Presenter

    private var hike: HikeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.selectedHikeComponent?.inject(this)
        hike = arguments?.getParcelable(SELECTED_HIKE)

        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_hike, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCategory.text = hike?.category
        tvHikeDate.text = hike?.dateOfHike
        tvRegion.text = hike?.region
        tvHikeChief.text = hike?.hikeChief
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }


    companion object {

        const val SELECTED_HIKE = "SelectedHike"

        fun getInstance(hike: HikeViewModel?): SelectedHikeFragment =
            SelectedHikeFragment().apply {
                arguments = Bundle().apply {
                    if (hike != null) {
                       putParcelable(SELECTED_HIKE, hike)
                    }
                }
            }
    }
}
