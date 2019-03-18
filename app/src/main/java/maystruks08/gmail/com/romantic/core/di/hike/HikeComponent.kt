package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikesFragment: HikeListFragment)

    fun inject(createHikeFragment: CreateNewHikeFragment)

}