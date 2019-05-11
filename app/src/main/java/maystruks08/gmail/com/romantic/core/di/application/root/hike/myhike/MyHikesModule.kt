package maystruks08.gmail.com.romantic.core.di.application.root.hike.myhike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.HikeRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractorImpl
import maystruks08.gmail.com.domain.repository.HikesRepository
import maystruks08.gmail.com.romantic.ui.hike.myhikes.MyHikePresenter
import maystruks08.gmail.com.romantic.ui.hike.myhikes.MyHikesContract

@Module
class MyHikesModule {

    @Provides
    @MyHikesScope
    fun presenter(myHikePresenter: MyHikePresenter): MyHikesContract.Presenter = myHikePresenter

}