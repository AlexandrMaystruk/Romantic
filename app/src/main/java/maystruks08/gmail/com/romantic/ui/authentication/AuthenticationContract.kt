package maystruks08.gmail.com.romantic.ui.authentication

import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface AuthenticationContract {

    interface View : BaseView

    interface Presenter {

        interface SignUp : BasePresenter<AuthenticationContract.View> {
            fun singup(email: String, password: String, displayName: String)
        }

        interface SignIn : BasePresenter<AuthenticationContract.View> {
            fun login(email: String, password: String)
        }

        interface LogOut : BasePresenter<AuthenticationContract.View> {
            fun logout()
        }
    }
}
