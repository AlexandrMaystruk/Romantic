package maystruks08.gmail.com.romantic.core.navigation

import maystruks08.gmail.com.romantic.ui.launcher.SingInFragment
import maystruks08.gmail.com.romantic.ui.launcher.SingUpFragment
import maystruks08.gmail.com.romantic.ui.main.RootTabFragment
import maystruks08.gmail.com.romantic.ui.news.NewsFragment

object Screens {

    object SignInScreen : AppScreen() {
        override fun getFragment() = SingInFragment.getInstance()
    }

    object SignUpScreen : AppScreen() {
        override fun getFragment() = SingUpFragment.getInstance()
    }

    object RootTabScreen : AppScreen() {
        override fun getFragment() = RootTabFragment.getInstance()
    }

    object NewsScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

    object MessageScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

    object EventScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

    object MyHikeScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

}