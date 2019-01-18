package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.BaseNavigationActivity
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.hikes.RootActivity


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikeFragment: HikeListFragment)
}