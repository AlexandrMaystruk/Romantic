package maystruks08.gmail.com.romantic.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import javax.inject.Inject

class SingInFragment : Fragment(), AuthenticationContract.View {

    @Inject
    lateinit var presenter: AuthenticationContract.Presenter.SignIn

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        App.authenticationComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_sing_in, container, false)
    }



    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        fun getInstance(): SingInFragment = SingInFragment()
    }
}