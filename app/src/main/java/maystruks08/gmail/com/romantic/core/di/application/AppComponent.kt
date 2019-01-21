package maystruks08.gmail.com.romantic.core.di.application

import dagger.Component
import maystruks08.gmail.com.romantic.core.di.authentication.AuthenticationComponent
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent
import maystruks08.gmail.com.romantic.ui.hikes.RootActivity
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun inject(rootActivity: RootActivity)

    fun hikeComponent(): HikeComponent

    fun newsComponent(): NewsComponent

    fun authenticationComponent(): AuthenticationComponent
}