package maystruks08.gmail.com.romantic.core.di.authentication

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.AuthenticationRepositoryImpl
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractor
import maystruks08.gmail.com.domain.interactor.authentication.AuthenticationInteractorImpl
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import maystruks08.gmail.com.romantic.ui.authentication.AuthenticationContract
import maystruks08.gmail.com.romantic.ui.authentication.LogoutPresenter
import maystruks08.gmail.com.romantic.ui.authentication.SignInPresenter
import maystruks08.gmail.com.romantic.ui.authentication.SignUpPresenter

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
    fun presenteSignIn(authenticationPresenter: SignInPresenter): AuthenticationContract.Presenter.SignIn =
        authenticationPresenter

    @Provides
    @AuthenticationScope
    fun presenterLogOut(authenticationPresenter: LogoutPresenter): AuthenticationContract.Presenter.LogOut =
        authenticationPresenter

    @Provides
    @AuthenticationScope
    fun presenterSignUp(authenticationPresenter: SignUpPresenter): AuthenticationContract.Presenter.SignUp =
        authenticationPresenter


}