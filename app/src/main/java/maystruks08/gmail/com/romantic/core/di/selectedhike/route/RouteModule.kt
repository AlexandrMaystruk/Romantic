package maystruks08.gmail.com.romantic.core.di.selectedhike.route

import dagger.Binds
import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.RouteRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.NewsDAO
import maystruks08.gmail.com.domain.interactor.route.RouteInteractor
import maystruks08.gmail.com.domain.interactor.route.RouteInteractorImpl
import maystruks08.gmail.com.domain.repository.RouteRepository
import maystruks08.gmail.com.romantic.ui.selectedhike.route.RouteContract
import maystruks08.gmail.com.romantic.ui.selectedhike.route.RoutePresenter

@Module
abstract class RouteModule {

    @Binds
    @RouteScope
    abstract fun repository(routeRepository: RouteRepositoryImpl): RouteRepository

    @Binds
    @RouteScope
    abstract fun interactor(routeInteractor: RouteInteractorImpl): RouteInteractor

    @Binds
    @RouteScope
    abstract fun presenter(routePresenter: RoutePresenter): RouteContract.Presenter


}