package maystruks08.gmail.com.romantic.ui.main

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface LogOutContract {

    interface View : BaseView{

         fun startLauncherActivity()
    }

    interface Presenter : BasePresenter<View> {

        fun logout()

    }
}
