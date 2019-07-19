package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import maystruks08.gmail.com.data.fromJson
import maystruks08.gmail.com.data.fromJsonOrNull
import maystruks08.gmail.com.data.room.entity.RouteTable
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.POJORoute
import javax.inject.Inject

class RouteMapper @Inject constructor(val gson: Gson) {

    fun toRoute(routePOJO: RouteTable): Route {
        return routePOJO.let {
            Route(
                it.id,
                it.name,
                it.hikeId,
                RouteType.fromId(it.type),
                gson.fromJson(it.points),
                gson.fromJsonOrNull(it.completeRoutePath)
            )
        }
    }

    fun toRouteTable(route: Route): RouteTable {
        return route.let {
            RouteTable(
                it.id,
                it.name,
                it.hikeId,
                it.type.id,
                gson.toJson(it.points),
                gson.toJson(it.completeRoutePath)
            )
        }
    }

    fun toRoutePOJO(routeTable: RouteTable): POJORoute {
        return routeTable.let {
            POJORoute(
                it.id,
                it.name,
                it.hikeId,
                it.type,
                gson.fromJson(it.points),
                gson.fromJsonOrNull(it.completeRoutePath),
                it.image
            )
        }
    }

    fun toRoute(routePOJO: POJORoute): Route {
        return routePOJO.let {
            Route(
                it.id,
                it.name,
                it.hikeId,
                RouteType.fromId(it.routeType),
                it.points.toMutableList(),
                it.completeRoutePath?.toMutableList()
            )
        }
    }

    fun toRoute(route: Route): RouteTable {
        return route.let {
            RouteTable(
                it.id,
                it.name,
                it.hikeId,
                it.type.id,
                gson.toJson(it.points),
                gson.toJson(it.completeRoutePath)
            )
        }
    }

    private fun toFireStoreRote(route: Route): POJORoute {
        return route.let {
            POJORoute(
                it.id,
                it.name,
                it.hikeId,
                it.type.id,
                it.points,
                it.completeRoutePath,
                it.image
            )
        }
    }

}