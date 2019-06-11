package maystruks08.gmail.com.romantic.ui.profilesettings

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
import maystruks08.gmail.com.romantic.ui.viewmodel.UserViewModel
import javax.inject.Inject

class ProfileSettingsFragment : Fragment(), ProfileSettingsContract.View {

    @Inject
    lateinit var presenter: ProfileSettingsContract.Presenter


    @Inject
    lateinit var controller: ToolBarController

    private var user: UserViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.profileComponent?.inject(this)
        user = arguments?.getParcelable(CURRENT_USER)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("Profile settings")
                .navigationIcon(R.drawable.ic_arrow_back_white_24dp)
                .bottomBarVisibility(false)
                .build()
            , activity as ConfigToolbar
        )
    }


    private fun initViews() {
        tvUserName?.text = user?.email
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        private const val CURRENT_USER = "CurrentUser"

        fun getInstance(user: UserViewModel): ProfileSettingsFragment = ProfileSettingsFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(CURRENT_USER, user)
                }
            }

    }
}