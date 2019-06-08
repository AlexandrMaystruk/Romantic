package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route.build

import dagger.Binds
import dagger.Module
import maystruks08.gmail.com.data.repository.RouteBuilderImpl
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build.BuildRouteInteractor
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.build.BuildRouteInteractorImpl
import maystruks08.gmail.com.domain.repository.RouteBuilder
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route.RouteScope
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute.BuildRoutePresenter
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute.BuildRouteContract

@Module
abstract class BuildRouteModule {

    @Binds
    @BuildRouteScope
    abstract fun presenter(buildRoutePresenter: BuildRoutePresenter): BuildRouteContract.Presenter

    @Binds
    @BuildRouteScope
    abstract fun interactor(buildRouteInteractor: BuildRouteInteractorImpl): BuildRouteInteractor

    @Binds
    @BuildRouteScope
    abstract fun routeBuilder(routeBuilderImpl: RouteBuilderImpl): RouteBuilder

}