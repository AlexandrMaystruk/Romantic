package maystruks08.gmail.com.romantic.core.di.profile

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.profile.ProfileFragment


@Subcomponent(modules = [ProfileModule::class])
@ProfileScope
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

}