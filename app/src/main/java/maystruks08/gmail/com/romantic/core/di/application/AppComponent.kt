package maystruks08.gmail.com.romantic.core.di.application

import dagger.Component
import maystruks08.gmail.com.romantic.core.di.root.RootComponent
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun rootComponent(): RootComponent

}