package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class LogoutPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    LogOutContract.Presenter, ABasePresenter<LogOutContract.View>() {

    @Inject
    lateinit var router: Router

    override fun logout() {
        view?.showLoading()
        compositeDisposable.add(
            authenticationInteractor.logout()
                .subscribe(::onLogoutSuccess, ::onLogoutFailure)
        )
    }


    private fun onLogoutSuccess() {
        view?.hideLoading()

    }

    private fun onLogoutFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()

    }
}
