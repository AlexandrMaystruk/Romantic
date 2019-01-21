package maystruks08.gmail.com.romantic.core.di.authentication

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.authentication.SingInFragment
import maystruks08.gmail.com.romantic.ui.authentication.SingUpFragment

@Subcomponent(modules = [AuthenticationModule::class])
@AuthenticationScope
interface AuthenticationComponent {

    fun inject(signInFragment: SingInFragment)

    fun inject(signIpFragment: SingUpFragment)


}