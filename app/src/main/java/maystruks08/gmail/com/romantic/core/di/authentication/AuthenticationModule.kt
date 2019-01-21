package maystruks08.gmail.com.romantic.core.di.authentication

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.AuthenticationRepositoryImpl
import maystruks08.gmail.com.data.repository.NewsRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.NewsDAO
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractorImpl
import maystruks08.gmail.com.domain.interactor.news.NewsInteractor
import maystruks08.gmail.com.domain.interactor.news.NewsInteractorImpl
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import maystruks08.gmail.com.domain.repository.NewsRepository
import maystruks08.gmail.com.romantic.ui.authentication.AuthenticationContract
import maystruks08.gmail.com.romantic.ui.authentication.LoginPresenter
import maystruks08.gmail.com.romantic.ui.news.NewsContract
import maystruks08.gmail.com.romantic.ui.news.NewsPresenter

@Module
class AuthenticationModule {

    @Provides
    @AuthenticationScope
    fun repository(authenticationRepository: AuthenticationRepositoryImpl): AuthenticationRepository =
        authenticationRepository

    @Provides
    @AuthenticationScope
    fun interactor(authenticationInteractorImpl: AuthenticationInteractorImpl): AuthenticationInteractor =
        authenticationInteractorImpl

    @Provides
    @AuthenticationScope
    fun presenterLogOut(authenticationPresenter: LoginPresenter): AuthenticationContract.Presenter.LogOut =
        authenticationPresenter

    @Provides
    @AuthenticationScope
    fun presenterSingIn(authenticationPresenter: LoginPresenter): AuthenticationContract.Presenter.SignIn =
        authenticationPresenter

    @Provides
    @AuthenticationScope
    fun presenterSignUp(authenticationPresenter: LoginPresenter): AuthenticationContract.Presenter.SignUp =
        authenticationPresenter


}