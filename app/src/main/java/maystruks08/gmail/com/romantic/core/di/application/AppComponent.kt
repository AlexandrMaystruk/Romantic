package maystruks08.gmail.com.romantic.core.di.application

import dagger.Component
import maystruks08.gmail.com.romantic.core.di.application.launcher.LauncherComponent
import maystruks08.gmail.com.romantic.core.di.application.root.RootComponent
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun launcherComponent(): LauncherComponent

    fun rootComponent(): RootComponent
}