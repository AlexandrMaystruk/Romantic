package maystruks08.gmail.com.romantic.core.navigation

import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.news.NewsFragment

object Screens {

    object HikeListScreen : AppScreen() {
        override fun getFragment() = HikeListFragment.getInstance()
    }

    object NewsScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

}