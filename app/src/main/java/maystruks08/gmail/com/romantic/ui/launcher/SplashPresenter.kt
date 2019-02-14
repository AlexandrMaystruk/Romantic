package maystruks08.gmail.com.romantic.ui.launcher

import maystruks08.gmail.com.domain.interactor.launcher.AuthenticationInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router

import javax.inject.Inject


class SplashPresenter @Inject constructor(private val authenticationInteractor: AuthenticationInteractor) :
    SplashContract.Presenter, ABasePresenter<SplashContract.View>() {

    @Inject
    lateinit var router: Router

    override fun isAuthenticated() {
        compositeDisposable.add(
            authenticationInteractor.getCurrentUser()
                .subscribe(::navigateToMainScreen, ::navigateToLoginScreen)
        )
    }

    override fun onBackPressed() {
        router.exit()
    }

    private fun navigateToMainScreen() {
        view?.startRootActivity()
    }

    private fun navigateToLoginScreen(t: Throwable) {
        t.printStackTrace()
        router.newRootScreen(Screens.SignInScreen)
    }


}
