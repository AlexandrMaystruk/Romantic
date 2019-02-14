package maystruks08.gmail.com.domain.interactor.launcher

import io.reactivex.Completable
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    private val repository: AuthenticationRepository
) : AuthenticationInteractor {

    override fun getCurrentUser(): Completable {
        return repository.getCurrentUser()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun singUp(email: String, password: String, displayName: String): Completable {
        return repository.signUp(email, password, displayName)
    }

    override fun login(email: String, password: String): Completable {
        return repository.login(email, password)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }


}