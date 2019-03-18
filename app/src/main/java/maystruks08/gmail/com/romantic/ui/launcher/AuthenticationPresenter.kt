package maystruks08.gmail.com.romantic.ui.launcher

import maystruks08.gmail.com.domain.interactor.launcher.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class AuthenticationPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    AuthenticationContract.Presenter, BasePresenter<AuthenticationContract.View>() {

    @Inject
    lateinit var router: Router

    override fun login(email: String, password: String) {
        view?.showLoading()
        view?.blockUi(false)
        if (email.length > 4 && password.length > 4) {
            compositeDisposable.add(
                authenticationInteractor.login(email, password)
                    .subscribe(::onSignInSuccess, ::onSignInFailure)
            )
        } else {
            view?.hideLoading()
            view?.blockUi(true)
            view?.showMessage("Field input!!!")
        }
    }

    override fun signUp(email: String, password: String, displayName: String) {
        view?.showLoading()
        view?.blockUi(false)
        if (email.length > 4 && password.length > 4 && displayName != "") {
            compositeDisposable.add(
                authenticationInteractor.singUp(email, password, displayName)
                    .subscribe(::onSignUpSuccess, ::onSignUpFailure)
            )
        } else {
            view?.hideLoading()
            view?.blockUi(true)
            view?.showMessage("Field input!!!")
        }
    }

    override fun onNavigateToSingUpClick() {
        view?.blockUi(true)
        router.navigateTo(Screens.SignUpScreen())
    }

    private fun onSignInSuccess() {
        view?.hideLoading()
        view?.blockUi(true)
        view?.startRootActivity()
    }

    private fun onSignInFailure(t: Throwable) {
        view?.hideLoading()
        view?.blockUi(true)
        view?.showError(t)
    }

    private fun onSignUpSuccess() {
        view?.hideLoading()
        view?.blockUi(true)
        view?.startRootActivity()
    }

    private fun onSignUpFailure(t: Throwable) {
        view?.hideLoading()
        view?.blockUi(true)
        view?.showError(t)
    }
}
