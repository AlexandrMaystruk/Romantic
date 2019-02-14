package maystruks08.gmail.com.romantic.core.di.application

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.LogOutRepositoryImpl
import maystruks08.gmail.com.domain.interactor.authentication.LogOutInteractor
import maystruks08.gmail.com.domain.interactor.authentication.LogOutInteractorImpl
import maystruks08.gmail.com.domain.repository.LogOutRepository

import maystruks08.gmail.com.romantic.ui.authentication.LogOutContract
import maystruks08.gmail.com.romantic.ui.authentication.LogoutPresenter
import javax.inject.Singleton

@Module
class LogOutModule {

    @Provides
    @Singleton
    fun repository(logOutRepositoryImpl: LogOutRepositoryImpl): LogOutRepository = logOutRepositoryImpl

    @Provides
    @Singleton
    fun interactor(logOutInteractorImpl: LogOutInteractorImpl): LogOutInteractor = logOutInteractorImpl

    @Provides
    @Singleton
    fun presenter(logoutPresenter: LogoutPresenter): LogOutContract.Presenter = logoutPresenter

}