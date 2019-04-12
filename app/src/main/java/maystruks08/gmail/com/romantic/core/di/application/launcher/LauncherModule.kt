package maystruks08.gmail.com.romantic.core.di.application.launcher

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.AuthenticationRepositoryImpl
import maystruks08.gmail.com.domain.interactor.launcher.AuthenticationInteractor
import maystruks08.gmail.com.domain.interactor.launcher.AuthenticationInteractorImpl
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import maystruks08.gmail.com.romantic.ui.launcher.AuthenticationContract
import maystruks08.gmail.com.romantic.ui.launcher.AuthenticationPresenter
import maystruks08.gmail.com.romantic.ui.launcher.SplashContract
import maystruks08.gmail.com.romantic.ui.launcher.SplashPresenter

@Module
class LauncherModule {

    @Provides
    @LauncherScope
    fun repository(authenticationRepository: AuthenticationRepositoryImpl): AuthenticationRepository =
        authenticationRepository


    @Provides
    @LauncherScope
    fun interactor(authenticationInteractorImpl: AuthenticationInteractorImpl): AuthenticationInteractor =
        authenticationInteractorImpl

    @Provides
    @LauncherScope
    fun presenteSplash(splashPresenter: SplashPresenter): SplashContract.Presenter =
        splashPresenter

    @Provides
    @LauncherScope
    fun presenter(authenticationPresenter: AuthenticationPresenter): AuthenticationContract.Presenter =
        authenticationPresenter
}