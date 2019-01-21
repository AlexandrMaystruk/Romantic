package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter

import javax.inject.Inject


class SignInPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter.SignIn, ABasePresenter<AuthenticationContract.View>() {

    override fun login(email: String, password: String) {
        compositeDisposable.add(
            authenticationInteractor
                .login(email, password)
                .subscribe()
        )
    }
}
