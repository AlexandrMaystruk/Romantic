package maystruks08.gmail.com.romantic.ui.launcher

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView

interface AuthenticationContract {

    interface View : IView {
         fun startRootActivity()

        fun blockUi(enable: Boolean)

        fun showMessage(message: String)
    }

    interface Presenter : IPresenter<View> {

        fun signUp(email: String, password: String, displayName: String)

        fun login(email: String, password: String)

        fun onNavigateToSingUpClick()

    }
}
