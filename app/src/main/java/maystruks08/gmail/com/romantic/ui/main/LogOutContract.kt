package maystruks08.gmail.com.romantic.ui.main

import maystruks08.gmail.com.romantic.core.base.IActivityPresenter
import maystruks08.gmail.com.romantic.core.base.IActivityView


interface LogOutContract {

    interface View : IActivityView {

         fun startLauncherActivity()

        fun showLoading ()

        fun hideLoading ()

        fun showError (t: Throwable)
    }

    interface Presenter : IActivityPresenter<View> {

        fun logout()

    }
}
