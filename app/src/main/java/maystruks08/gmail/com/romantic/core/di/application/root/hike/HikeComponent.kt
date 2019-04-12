package maystruks08.gmail.com.romantic.core.di.application.root.hike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.createhike.CreateHikeComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.myhike.MyHikesComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.SelectedHikeComponent
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.main.pager.RootTabFragment


@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(rootTabFragment: RootTabFragment)

    fun createComponent(): CreateHikeComponent

    fun myHikesComponent(): MyHikesComponent

    fun selectedHikeComponent(): SelectedHikeComponent
}