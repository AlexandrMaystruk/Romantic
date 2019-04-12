package maystruks08.gmail.com.romantic.core.di.application.root

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.LogOutRepositoryImpl
import maystruks08.gmail.com.data.repository.UploadRepositoryImpl
import maystruks08.gmail.com.domain.interactor.main.RootInteractor
import maystruks08.gmail.com.domain.interactor.main.RootInteractorImpl
import maystruks08.gmail.com.domain.repository.LogOutRepository
import maystruks08.gmail.com.domain.repository.UploadRepository

import maystruks08.gmail.com.romantic.ui.main.RootContract
import maystruks08.gmail.com.romantic.ui.main.RootPresenter
import javax.inject.Singleton

@Module
class RootActivityModule {

    @Provides
    @RootScope
    fun upload(uploadRepositoryImpl: UploadRepositoryImpl): UploadRepository = uploadRepositoryImpl

    @Provides
    @RootScope
    fun repository(rootRepositoryImpl: LogOutRepositoryImpl): LogOutRepository = rootRepositoryImpl

    @Provides
    @RootScope
    fun interactor(rootInteractorImpl: RootInteractorImpl): RootInteractor = rootInteractorImpl

    @Provides
    @RootScope
    fun presenter(rootPresenter: RootPresenter): RootContract.Presenter = rootPresenter

}