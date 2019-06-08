package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.data.room.entity.RouteTable
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.POJORoute
import javax.inject.Inject

class RouteMapper @Inject constructor() {


    fun toRoute(routePOJO: RouteTable): Route {
        return routePOJO.let {
            Route(
                it.id,
                RouteType.fromIndex(it.type),
                Gson().fromJson(it.geoPoints)
            )
        }
    }

    fun toRoute(routePOJO: POJORoute): Route {
        return routePOJO.let {
            Route(
                it.id,
                RouteType.fromIndex(it.routeType),
                it.route.toMutableList()
            )
        }
    }


    fun toRoute(route: Route): RouteTable {
        return route.let {
            RouteTable(
                it.id,
                it.type.id,
                Gson().toJson(it.points)
            )
        }
    }

    private fun toFireStoreRote(route: Route): POJORoute {
        return route.let {
            POJORoute(
                it.id,
                it.type.id,
                it.points
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}