package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationInteractorImpl @Inject constructor(val repository: AuthenticationRepository) : AuthenticationInteractor {
    override fun singup(email: String, password: String, displayName: String): Single<User> {

       return Single.just(User(""))
    }

    override fun login(email: String, password: String): Completable {
      return Completable.complete()
    }

    override fun logout(): Completable {
        return Completable.complete()
    }
}