package maystruks08.gmail.com.romantic.core.di.application

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.romantic.ui.ToolBarController
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun router(): Router = cicerone.router

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun toolbarController(): ToolBarController = ToolBarController()

}