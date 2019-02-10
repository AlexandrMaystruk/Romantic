package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface AuthenticationContract {

    interface View : BaseView{
         fun startRootActivity()
    }

    interface Presenter : BasePresenter<AuthenticationContract.View> {

        fun signUp(email: String, password: String, displayName: String)

        fun login(email: String, password: String)

        fun onNavigateToSingUpClick()

    }
}
