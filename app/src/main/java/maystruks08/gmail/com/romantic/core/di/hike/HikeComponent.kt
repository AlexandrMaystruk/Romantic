package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.hike.HikeFragment


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikesFragment: HikeListFragment)

    fun inject(hikeFragment: HikeFragment)
}