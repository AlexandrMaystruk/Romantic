package maystruks08.gmail.com.romantic.core.di.application.root.hike.createhike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.main.pager.RootTabFragment


@Subcomponent(modules = [CreateHikeModule::class])
@CreateHikeScope
interface CreateHikeComponent {

    fun inject(createHikeFragment: CreateNewHikeFragment)

}