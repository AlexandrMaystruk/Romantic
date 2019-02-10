package maystruks08.gmail.com.romantic.core.navigation

import maystruks08.gmail.com.romantic.ui.authentication.SingInFragment
import maystruks08.gmail.com.romantic.ui.authentication.SingUpFragment
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.news.NewsFragment

object Screens {

    object SignInScreen : AppScreen() {
        override fun getFragment() = SingInFragment.getInstance()
    }

    object SignUpScreen : AppScreen() {
        override fun getFragment() = SingUpFragment.getInstance()
    }

    object HikeListScreen : AppScreen() {
        override fun getFragment() = HikeListFragment.getInstance()
    }

    object NewsScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

}