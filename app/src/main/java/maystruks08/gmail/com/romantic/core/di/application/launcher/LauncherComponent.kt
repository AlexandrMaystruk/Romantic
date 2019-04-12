package maystruks08.gmail.com.romantic.core.di.application.launcher

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.launcher.SingInFragment
import maystruks08.gmail.com.romantic.ui.launcher.SingUpFragment
import maystruks08.gmail.com.romantic.ui.launcher.SplashActivity

@Subcomponent(modules = [LauncherModule::class])
@LauncherScope
interface LauncherComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(signInFragment: SingInFragment)

    fun inject(signIpFragment: SingUpFragment)

}