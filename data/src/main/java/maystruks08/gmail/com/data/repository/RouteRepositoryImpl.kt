package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.RouteMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.repository.RouteRepository
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor(
    private val routeMapper: RouteMapper,
    private val hikeDao: HikeDAO,
    private val api: FireStoreApi
) : RouteRepository {


    override fun saveRote(hikeId: Long, route: Route): Single<Route> {
        return Completable.fromAction {
            hikeDao.addRoteToHike(hikeId, routeMapper.toRouteTable(route))
        }.toSingle { route }
    }

    override fun saveRotes(hikeId: Long, routes: List<Route>): Single<List<Route>> {
        return Completable.fromAction {
            routes.forEach {
                return@fromAction hikeDao.addRoteToHike(hikeId, routeMapper.toRouteTable(it))
            }
        }.toSingle { routes }
    }

    override fun getRoute(routeId: Long): Single<Route> {
        return hikeDao.getRouteById(routeId).map {
            routeMapper.toRoute(it)
        }
    }

    override fun getRoutes(hikeId: Long): Single<List<Route>> {
        return hikeDao.getRoutesByHikeId(hikeId).map { list ->
            list.map { routeMapper.toRoute(it) }
        }
    }

    override fun removeRoute(hikeId: Long, routeId: Long): Completable {
        return Completable.complete()
    }
}