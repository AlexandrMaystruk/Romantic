package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter

import javax.inject.Inject


class LoginPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter.SignIn,
    AuthenticationContract.Presenter.LogOut,
    AuthenticationContract.Presenter.SignUp
    , ABasePresenter<AuthenticationContract.View>() {


    override fun singup(email: String, password: String, displayName: String) {

    }

    override fun logout() {

    }

    override fun login(email: String, password: String) {

    }


}
