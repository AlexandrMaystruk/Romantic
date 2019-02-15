package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.hikes.HikeListContract
import maystruks08.gmail.com.romantic.ui.hikes.HikeListPresenter
import maystruks08.gmail.com.romantic.ui.hike.HikeContract
import maystruks08.gmail.com.romantic.ui.hike.HikePresenter

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



//todo move to another scope
    @Provides
    @HikeScope
    fun SelectedHikePresenter(hikePresenter: HikePresenter): HikeContract.Presenter = hikePresenter



}