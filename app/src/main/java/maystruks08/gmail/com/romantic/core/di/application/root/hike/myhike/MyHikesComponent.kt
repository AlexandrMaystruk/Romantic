package maystruks08.gmail.com.romantic.core.di.application.root.hike.myhike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hike.myhikes.MyHikesFragment


@Subcomponent(modules = [MyHikesModule::class])
@MyHikesScope
interface MyHikesComponent {

    fun inject(myHikesFragment: MyHikesFragment)

}