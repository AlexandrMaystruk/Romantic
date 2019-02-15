package maystruks08.gmail.com.romantic.ui.launcher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.main.RootActivity
import javax.inject.Inject

class SingUpFragment : Fragment(), AuthenticationContract.View {

    @Inject
    lateinit var presenter: AuthenticationContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        App.launcherComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btnRegister.setOnClickListener {
            presenter.signUp(etEmail.text.toString(), etPassword.text.toString(), etDisplayName.text.toString())
        }
    }

    override fun startRootActivity() {
        startActivity(Intent(activity, RootActivity::class.java))
        activity?.finish()
    }


    override fun configToolbar() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        fun getInstance(): SingUpFragment =
            SingUpFragment()
    }
}