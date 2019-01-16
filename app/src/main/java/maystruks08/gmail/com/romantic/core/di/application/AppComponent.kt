package maystruks08.gmail.com.romantic.core.di.application

import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import dagger.Component
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun hikeComponent(): HikeComponent

}