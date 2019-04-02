package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.main.pager.RootTabFragment


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(rootTabFragment: RootTabFragment)

    fun inject(createHikeFragment: CreateNewHikeFragment)

}