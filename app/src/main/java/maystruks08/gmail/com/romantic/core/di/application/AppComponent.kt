package maystruks08.gmail.com.romantic.core.di.application

import dagger.Component
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.launcher.LauncherComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent
import maystruks08.gmail.com.romantic.ui.hikes.RootActivity
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun inject(rootActivity: RootActivity)

    fun launcherComponent(): LauncherComponent

    fun hikeComponent(): HikeComponent

    fun newsComponent(): NewsComponent
}