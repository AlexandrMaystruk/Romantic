package maystruks08.gmail.com.romantic.core.di.myhike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.myhikes.MyHikePresenter
import maystruks08.gmail.com.romantic.ui.myhikes.MyHikesContract

@Module
class MyHikesModule {

    @Provides
    @MyHikesScope
    fun hikeDao(appDatabase: AppDatabase): HikeDAO = appDatabase.hikeDao()

    @Provides
    @MyHikesScope
    fun repository(hikesRepository: HikeRepositoryImpl): HikesRepository = hikesRepository

    @Provides
    @MyHikesScope
    fun interactor(hikeInteractorImpl: HikeInteractorImpl): HikeInteractor = hikeInteractorImpl

    @Provides
    @MyHikesScope
    fun presenter(myHikePresenter: MyHikePresenter): MyHikesContract.Presenter = myHikePresenter

}