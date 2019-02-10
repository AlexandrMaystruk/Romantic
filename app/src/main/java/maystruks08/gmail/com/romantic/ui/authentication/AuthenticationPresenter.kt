package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class AuthenticationPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter, ABasePresenter<AuthenticationContract.View>() {

    @Inject
    lateinit var router: Router

    override fun login(email: String, password: String) {
        view?.showLoading()
        compositeDisposable.add(
            authenticationInteractor.login(email, password)
                .subscribe(::onSignInSuccess, ::onSignInFailure)
        )
    }

    override fun signUp(email: String, password: String, displayName: String) {
        view?.showLoading()
        compositeDisposable.add(
            authenticationInteractor.singUp(email, password, displayName)
                .subscribe(::onSignUpSuccess, ::onSignUpFailure)
        )
    }

    override fun onNavigateToSingUpClick() {
        router.navigateTo(Screens.SignUpScreen)
    }

    private fun onSignInSuccess() {
        view?.hideLoading()
        view?.startRootActivity()
    }

    private fun onSignInFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    private fun onSignUpSuccess() {
        view?.hideLoading()
        view?.startRootActivity()
    }

    private fun onSignUpFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }
}
