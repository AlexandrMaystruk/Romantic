package maystruks08.gmail.com.romantic.core.di.profile

import dagger.Module
import dagger.Provides

import maystruks08.gmail.com.romantic.ui.profile.ProfileContract
import maystruks08.gmail.com.romantic.ui.profile.ProfilePresenter
import maystruks08.gmail.com.romantic.ui.profilesettings.ProfileSettingsContract
import maystruks08.gmail.com.romantic.ui.profilesettings.ProfileSettingsPresenter

@Module
class ProfileModule {

    @Provides
    @ProfileScope
    fun presenter(profilePresenter: ProfilePresenter): ProfileContract.Presenter = profilePresenter


    @Provides
    @ProfileScope
    fun presenterSettings(profileSettingsPresenter: ProfileSettingsPresenter): ProfileSettingsContract.Presenter = profileSettingsPresenter

}