package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.POJORoute
import javax.inject.Inject

class RouteMapper @Inject constructor() {

    fun toRoute(routePOJO: POJORoute): Route {
        return routePOJO.let {
            Route(
                it.id,
                RouteType.fromIndex(it.routeType),
                it.route.toMutableList()
            )
        }
    }

    private fun toFireBaseHike(route: Route): POJORoute {
        return route.let {
            POJORoute(
                it.id,
                it.type.id,
                it.geoPoints
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}