package maystruks08.gmail.com.romantic.core.di.hike


import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.BaseNavigationActivity
import maystruks08.gmail.com.romantic.ui.hikes.HikesActivity

@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikeActivity: HikesActivity)

    fun inject(hikeActivity: BaseNavigationActivity)


}