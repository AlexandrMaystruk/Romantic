package maystruks08.gmail.com.romantic.core.di.application.root.hike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeContract
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikePresenter
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListContract
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListPresenter

@Module
class HikeModule {

    @Provides
    @HikeScope
    fun repository(hikesRepository: HikeRepositoryImpl): HikesRepository = hikesRepository

    @Provides
    @HikeScope
    fun interactor(hikeInteractorImpl: HikeInteractorImpl): HikeInteractor = hikeInteractorImpl

    @Provides
    @HikeScope
    fun presenter(hikeListPresenter: HikeListPresenter): HikeListContract.Presenter = hikeListPresenter

}