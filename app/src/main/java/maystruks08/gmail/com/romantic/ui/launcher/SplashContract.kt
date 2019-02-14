package maystruks08.gmail.com.romantic.ui.launcher

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface SplashContract {

    interface View : BaseView {

        fun startRootActivity()

    }


    interface Presenter : BasePresenter<SplashContract.View> {

        fun isAuthenticated()

        fun onBackPressed()

    }
}
