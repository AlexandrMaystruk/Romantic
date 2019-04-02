package maystruks08.gmail.com.romantic.core.di.profile

import dagger.Module
import dagger.Provides

import maystruks08.gmail.com.romantic.ui.profile.ProfileContract
import maystruks08.gmail.com.romantic.ui.profile.ProfilePresenter

@Module
class ProfileModule {

    @Provides
    @ProfileScope
    fun presenter(profilePresenter: ProfilePresenter): ProfileContract.Presenter = profilePresenter


}