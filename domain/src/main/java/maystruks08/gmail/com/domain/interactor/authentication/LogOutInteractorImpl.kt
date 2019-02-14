package maystruks08.gmail.com.domain.interactor.authentication

import io.reactivex.Completable
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.LogOutRepository
import javax.inject.Inject

class LogOutInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    private val repository: LogOutRepository
) : LogOutInteractor {



    override fun logout(): Completable {
        return repository.logout()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }
}