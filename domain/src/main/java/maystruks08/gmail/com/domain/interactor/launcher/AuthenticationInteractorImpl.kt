package maystruks08.gmail.com.domain.interactor.launcher

import io.reactivex.Completable
import io.reactivex.Single
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
        return repository.createFireBaseUser(email, password, displayName)
            .flatMapCompletable { user ->
                return@flatMapCompletable repository.addUserToDb(user)
                    .subscribeOn(executor.mainExecutor)
                    .observeOn(executor.postExecutor)
                    .andThen(repository.addUserToFireStoreDb(user))
                    .andThen(repository.saveUserToPref(user))
            }
    }

    override fun login(email: String, password: String): Completable {
        return repository.login(email, password)
            .flatMapCompletable { user ->
                return@flatMapCompletable repository.addUserToDb(user)
                    .andThen(repository.saveUserToPref(user))
            }
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}
