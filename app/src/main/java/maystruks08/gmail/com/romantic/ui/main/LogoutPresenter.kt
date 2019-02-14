package maystruks08.gmail.com.romantic.ui.main

import maystruks08.gmail.com.domain.interactor.main.LogOutInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject


class LogoutPresenter @Inject constructor(private val logOutInteractor: LogOutInteractor) :
    LogOutContract.Presenter, ABasePresenter<LogOutContract.View>() {

    override fun logout() {
        view?.showLoading()
        compositeDisposable.add(
            logOutInteractor.logout()
                .subscribe(::onLogoutSuccess, ::onLogoutFailure)
        )
    }

    private fun onLogoutSuccess() {
        view?.hideLoading()
        view?.startLauncherActivity()
    }

    private fun onLogoutFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()

    }
}
