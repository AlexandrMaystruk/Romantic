package maystruks08.gmail.com.romantic.core.di.application.root.hike.createhike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.interactor.hike.create.CreateHikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.create.CreateHikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeContract
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikePresenter
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListContract
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListPresenter

@Module
class CreateHikeModule {

    @Provides
    @CreateHikeScope
    fun presenterCreate(createNewPresenter: CreateNewHikePresenter): CreateNewHikeContract.Presenter = createNewPresenter

    @Provides
    @CreateHikeScope
    fun interactor(createHikeInteractorImpl: CreateHikeInteractorImpl): CreateHikeInteractor = createHikeInteractorImpl

}