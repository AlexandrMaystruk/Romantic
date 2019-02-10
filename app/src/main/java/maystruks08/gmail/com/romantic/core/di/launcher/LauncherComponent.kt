package maystruks08.gmail.com.romantic.core.di.launcher

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.authentication.SingInFragment
import maystruks08.gmail.com.romantic.ui.authentication.SingUpFragment
import maystruks08.gmail.com.romantic.ui.splash.SplashActivity

@Subcomponent(modules = [LauncherModule::class])
@LauncherScope
interface LauncherComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(signInFragment: SingInFragment)

    fun inject(signIpFragment: SingUpFragment)

}