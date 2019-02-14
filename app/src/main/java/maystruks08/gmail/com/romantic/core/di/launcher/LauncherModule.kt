package maystruks08.gmail.com.romantic.core.di.launcher

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.repository.AuthenticationRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.UserDAO
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

    @Provides
    @LauncherScope
    fun userMapper(): UserMapper = UserMapper()

    @Provides
    @LauncherScope
    fun userDao(appDatabase: AppDatabase): UserDAO = appDatabase.userDao()


}