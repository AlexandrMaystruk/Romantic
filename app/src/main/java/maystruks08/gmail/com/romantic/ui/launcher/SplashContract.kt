package maystruks08.gmail.com.romantic.ui.launcher

import maystruks08.gmail.com.romantic.core.base.IActivityPresenter
import maystruks08.gmail.com.romantic.core.base.IActivityView


interface SplashContract {

    interface View : IActivityView {

        fun startRootActivity()

    }

    interface Presenter : IActivityPresenter<View> {

        fun isAuthenticated()

        fun onBackPressed()

    }
}
