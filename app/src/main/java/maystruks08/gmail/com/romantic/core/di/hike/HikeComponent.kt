package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import maystruks08.gmail.com.romantic.ui.main.RootTabFragment


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikeFragment: HikeListFragment)

    fun inject(createHikeFragment: CreateNewHikeFragment)

    fun inject(tabFragment: RootTabFragment)
}