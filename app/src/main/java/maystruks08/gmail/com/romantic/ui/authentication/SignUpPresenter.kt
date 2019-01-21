package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter

import javax.inject.Inject


class SignUpPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter.SignUp, ABasePresenter<AuthenticationContract.View>() {

    override fun signUp(email: String, password: String, displayName: String) {
        compositeDisposable.add(
            authenticationInteractor
                .singup(email, password, displayName)
                .subscribe()
        )

    }

}
