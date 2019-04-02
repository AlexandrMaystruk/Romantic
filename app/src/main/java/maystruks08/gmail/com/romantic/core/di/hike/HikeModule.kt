package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeContract
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikePresenter
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListContract
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListPresenter
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikeContract
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikePresenter

@Module
class HikeModule {

    @Provides
    @HikeScope
    fun hikeDao(appDatabase: AppDatabase): HikeDAO = appDatabase.hikeDao()

    @Provides
    @HikeScope
    fun repository(hikesRepository: HikeRepositoryImpl): HikesRepository = hikesRepository

    @Provides
    @HikeScope
    fun interactor(hikeInteractorImpl: HikeInteractorImpl): HikeInteractor = hikeInteractorImpl

    @Provides
    @HikeScope
    fun presenter(hikeListPresenter: HikeListPresenter): HikeListContract.Presenter = hikeListPresenter

    @Provides
    @HikeScope
    fun presenterCreate(createNewPresenter: CreateNewHikePresenter): CreateNewHikeContract.Presenter = createNewPresenter

}