package maystruks08.gmail.com.romantic.core.di.hike


import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hikes.HikeListActivity

@Subcomponent(modules = [HikeModule::class])
@HikeScope
interface HikeComponent {

    fun inject(hikeActivity: HikeListActivity)

}