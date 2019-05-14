package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.route.RouteBuilder
import maystruks08.gmail.com.domain.repository.RouteRepository
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor(
    private val routeBuilder: RouteBuilder,
    private val hikeDao: HikeDAO,
    private val api: FireStoreApi
) : RouteRepository {
    override fun getRoute(hikeId: Long, routeId: Long): Single<Route> {
        return Single.just(
            Route(
                345345,
                RouteType.MAIN,
                mutableListOf(maystruks08.gmail.com.domain.entity.GeoPoint(325.0, 233.0))
            )
        )

    }

    override fun getRoutes(hikeId: Long): Single<List<Route>> {
        return Single.just(
            listOf(
                Route(
                    345345,
                    RouteType.MAIN,
                    mutableListOf(maystruks08.gmail.com.domain.entity.GeoPoint(325.0, 233.0))
                )
            )
        )
    }

    override fun addNewGeoPoint(
        hikeId: Long,
        routeId: Long,
        geoPoint: maystruks08.gmail.com.domain.entity.GeoPoint
    ): Single<Route> {
        return Single.just(
            Route(
                345345,
                RouteType.MAIN,
                mutableListOf(maystruks08.gmail.com.domain.entity.GeoPoint(325.0, 233.0))
            )
        )

    }

    override fun removeGeoPoint(
        hikeId: Long,
        routeId: Long,
        geoPoint: maystruks08.gmail.com.domain.entity.GeoPoint
    ): Single<Route> {
        return Single.just(
            Route(
                345345,
                RouteType.MAIN,
                mutableListOf(maystruks08.gmail.com.domain.entity.GeoPoint(325.0, 233.0))
            )
        )

    }

    override fun removeRoute(hikeId: Long, routeId: Long): Completable {
        return Completable.complete()
    }
}