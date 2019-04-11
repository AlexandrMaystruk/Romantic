package maystruks08.gmail.com.romantic.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import maystruks08.gmail.com.romantic.ui.viewmodel.ParticipantViewModel
import maystruks08.gmail.com.romantic.ui.viewmodel.UserViewModel
import javax.inject.Inject

class ProfileFragment : Fragment(), ProfileContract.View {

    @Inject
    lateinit var presenter: ProfileContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private var participant: ParticipantViewModel? = null

    private var user: UserViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.profileComponent?.inject(this)
        presenter.bindView(this)
        participant = arguments?.getParcelable(SELECTED_PARTICIPANT)
        user = arguments?.getParcelable(SELECTED_USER)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun configToolbar() {
        val displayName = participant?.displayName?: user?.displayName
        controller.configure(
            ToolbarDescriptor(
                true,
                "$displayName Profile",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ), activity as ConfigToolbar
        )
    }


    private fun initViews(){
        tvUserName?.text = participant?.displayName?: user?.displayName
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        private const val SELECTED_PARTICIPANT = "SelectedParticipant"

        private const val SELECTED_USER = "SelectedUser"

        fun getInstance(participant: ParticipantViewModel): ProfileFragment = ProfileFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(SELECTED_PARTICIPANT, participant)
                }
            }


        fun getInstance(user: UserViewModel): ProfileFragment = ProfileFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(SELECTED_USER, user)
                }
            }

    }
}