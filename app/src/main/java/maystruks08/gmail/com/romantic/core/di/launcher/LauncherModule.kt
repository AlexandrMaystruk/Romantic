package maystruks08.gmail.com.romantic.core.di.launcher

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.repository.AuthenticationRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractorImpl
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import maystruks08.gmail.com.romantic.ui.authentication.AuthenticationContract
import maystruks08.gmail.com.romantic.ui.authentication.AuthenticationPresenter
import maystruks08.gmail.com.romantic.ui.splash.SplashContract
import maystruks08.gmail.com.romantic.ui.splash.SplashPresenter

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

    @Provides
    @LauncherScope
    fun userMapper(): UserMapper = UserMapper()

    @Provides
    @LauncherScope
    fun userDao(appDatabase: AppDatabase): UserDAO = appDatabase.userDao()


}