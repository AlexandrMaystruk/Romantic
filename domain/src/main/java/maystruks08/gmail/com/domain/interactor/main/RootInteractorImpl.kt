package maystruks08.gmail.com.domain.interactor.main

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import maystruks08.gmail.com.domain.repository.LogOutRepository
import maystruks08.gmail.com.domain.repository.UploadRepository
import javax.inject.Inject

class RootInteractorImpl @Inject constructor(
    private val executor: ThreadExecutor,
    private val repository: LogOutRepository,
    private val uploadRepository: UploadRepository

) : RootInteractor {

    override fun uploadHikes(): Single<Int> {
        return uploadRepository.uploadNotUploadedHikes()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun updateHikes(): Single<Int> {
        return uploadRepository.updateHike()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun updateParticipants(): Single<Int> {
        return uploadRepository.updateParticipants()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun uploadParticipants(): Single<Int> {
        return uploadRepository.uploadNotUploadedParticipants()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }
    override fun updateRoutes(): Single<Int> {
        return uploadRepository.uploadNotUploadedParticipants()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)

    }

    override fun uploadRoutes(): Single<Int> {
        return uploadRepository.uploadNotUploadedParticipants()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

    override fun logout(): Completable {
        return repository.logout()
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }
}