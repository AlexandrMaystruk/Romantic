package maystruks08.gmail.com.romantic.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class ProfileFragment : Fragment(), ProfileContract.View {

    @Inject
    lateinit var presenter: ProfileContract.Presenter


    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.profileComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun configToolbar() {
        controller.configure(ToolbarDescriptor(true, "My Profile",  navigationIcon = R.drawable.ic_arrow_back_white_24dp, bottomBarVisibility = false ), activity as ConfigToolbar)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        fun getInstance(): ProfileFragment = ProfileFragment()
    }
}