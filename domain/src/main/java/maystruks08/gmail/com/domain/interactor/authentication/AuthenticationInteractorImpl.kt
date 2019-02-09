package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    private val repository: AuthenticationRepository
) : AuthenticationInteractor {

    override fun singup(email: String, password: String, displayName: String): Completable {
        return repository.signUp(email, password, displayName)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun login(email: String, password: String): Completable {
        return repository.login(email, password)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun logout(): Completable {
        return repository.logout()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }
}