package maystruks08.gmail.com.domain.interactor.authentication

import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationInteractorImpl @Inject constructor(val repository: AuthenticationRepository) : AuthenticationInteractor {
}