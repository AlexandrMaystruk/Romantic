package maystruks08.gmail.com.romantic.core.di.root

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.BaseNavigationActivity
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent
import maystruks08.gmail.com.romantic.ui.hikes.RootActivity


@Subcomponent(modules = [RootModule::class])
@RootScope
interface RootComponent {

    fun inject(rootActivity: RootActivity)

    fun inject(rootActivity: BaseNavigationActivity)

    fun hikeComponent(): HikeComponent

    fun newsComponent(): NewsComponent


}