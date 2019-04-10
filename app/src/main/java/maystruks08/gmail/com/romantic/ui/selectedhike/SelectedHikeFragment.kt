package maystruks08.gmail.com.romantic.ui.selectedhike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_selected_hike.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import javax.inject.Inject

class SelectedHikeFragment : Fragment(), SelectedHikeContract.View {

    @Inject
    lateinit var presenter: SelectedHikeContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private var hike: HikeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.selectedHikeComponent?.inject(this)
        hike = arguments?.getParcelable(SELECTED_HIKE)

        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_selected_hike, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCategory.text = hike?.category
        tvHikeDate.text = hike?.dateOfHike
        tvRegion.text = hike?.region
        tvHikeChief.text = hike?.hikeChief

        initViews()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(
                true, "Hike",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ),
            activity as ConfigToolbar
        )
    }

    private fun initViews() {
        civParticipant.setOnClickListener { presenter.onParticipantClick(hike?.id?.toString() ?: "") }
        civGroupChat.setOnClickListener { presenter.onGroupChatClick() }
        civTrainingCalendar.setOnClickListener { presenter.onTrainingCalendarClick() }
        civRoute.setOnClickListener { presenter.onRouteClick() }
        civMyPost.setOnClickListener { presenter.onMyPostClick() }
        civTools.setOnClickListener { presenter.onToolsClick() }
        civMaterials.setOnClickListener { presenter.onMaterialsClick() }
        civHikeInformation.setOnClickListener { presenter.onHikeInformationClick() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        App.clearSelectedHikeComponent()
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
