package maystruks08.gmail.com.domain.interactor.hike.selectedhike.route

import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteInteractor
import maystruks08.gmail.com.domain.repository.RouteRepository
import javax.inject.Inject

class RouteInteractorImpl @Inject constructor(val routeRepository: RouteRepository) :
    RouteInteractor {

    override fun buildRout() {

    }

    override fun addNewPoint() {
    }
}