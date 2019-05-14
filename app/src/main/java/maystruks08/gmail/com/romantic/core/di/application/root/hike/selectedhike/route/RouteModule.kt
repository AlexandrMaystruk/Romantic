package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route

import dagger.Binds
import dagger.Module
import maystruks08.gmail.com.data.repository.RouteBuilderImpl
import maystruks08.gmail.com.data.repository.RouteRepositoryImpl
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteBuilder
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractorImpl
import maystruks08.gmail.com.domain.repository.RouteRepository
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.RouteContract
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.RoutePresenter
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist.RouteListContract
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist.RouteListPresenter

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

    @Binds
    @RouteScope
    abstract fun routeBuilder(routeBuilderImpl: RouteBuilderImpl): RouteBuilder


    @Binds
    @RouteScope
    abstract fun routeListPresenter(routePresenter: RouteListPresenter): RouteListContract.Presenter

}