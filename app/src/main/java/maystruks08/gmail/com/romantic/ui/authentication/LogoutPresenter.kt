package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter

import javax.inject.Inject


class LogoutPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter.LogOut, ABasePresenter<AuthenticationContract.View>() {

    override fun logout() {
        compositeDisposable.add(
            authenticationInteractor
                .logout()
                .subscribe()
        )
    }
}
