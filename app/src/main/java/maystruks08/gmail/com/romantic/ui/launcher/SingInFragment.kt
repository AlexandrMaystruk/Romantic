package maystruks08.gmail.com.romantic.ui.launcher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sing_in.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.toast
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.main.RootActivity
import javax.inject.Inject

class SingInFragment : Fragment(), AuthenticationContract.View {

    @Inject
    lateinit var presenter: AuthenticationContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.launcherComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_sing_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tvSignUp.setOnClickListener {
            presenter.onNavigateToSingUpClick()
        }

        btnLogin.setOnClickListener {
            presenter.login(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    override fun startRootActivity() {
        this.startActivity(Intent(activity, RootActivity::class.java))
        activity?.finish()
    }

    override fun blockUi(enable: Boolean) {
        tvSignUp.isClickable = enable
        btnLogin.isClickable = enable
    }

    override fun configToolbar() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
        context?.toast(message)
    }

    override fun showError(t: Throwable) {
        t.printStackTrace()
    }

    companion object {

        fun getInstance(): SingInFragment =
            SingInFragment()
    }
}