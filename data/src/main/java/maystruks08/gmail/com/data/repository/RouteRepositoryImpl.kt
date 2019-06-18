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
    override fun saveRote(route: Route): Completable {
        return Completable.complete()
    }

    override fun saveRotes(routes: List<Route>): Completable {
        return Completable.complete()
    }

    override fun getRoute(routeId: Long): Single<Route> {
        return hikeDao.getRouteById(routeId).map {
            routeMapper.toRoute(it)
        }
    }

    override fun getRoutes(hikeId: Long): Single<List<Route>> {
//        return hikeDao.getRoutesByHikeId(hikeId).map { list ->
//            list.map { routeMapper.toRoute(it) }
//        }
        //todo remove stubs
        return Single.just(
            listOf(
                Route(
                    345345,
                    RouteType.MAIN,
                    mutableListOf(Point(325.0, 233.0)),
                    mutableListOf(Point(10.0, 30.0), Point(13.5, 32.5), Point(11.0, 33.0), Point(9.7, 22.0))
                ),
                Route(
                    345345,
                    RouteType.SPARE,
                    mutableListOf(Point(30.0, 42.0)),
                    mutableListOf(Point(49.0, 32.0), Point(49.1, 32.1), Point(49.5, 32.6), Point(49.1, 33.0))
                )
            )
        )
    }

    override fun removeRoute(hikeId: Long, routeId: Long): Completable {
        return Completable.complete()
    }
}