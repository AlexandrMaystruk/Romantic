package maystruks08.gmail.com.data.repository

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType
import maystruks08.gmail.com.domain.repository.RouteBuilder
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.util.*
import javax.inject.Inject

class RouteBuilderImpl @Inject constructor() : RouteBuilder {

    override fun buildRout(hikeId: Long, name: String, type: RouteType, listPoint: List<Point>): Single<Route> {
        return Single.create { emitter ->
            try {
                val route = Route(UUID.randomUUID().mostSignificantBits, name, hikeId, type, mutableListOf())
                listPoint.forEachIndexed { index, geoPoint ->
                    listPoint.getOrNull(index + 1)?.let {
                        route.addPath(getPathBetweenTwoPoints(geoPoint, it))
                    }
                }
                route.points.addAll(listPoint)
                emitter.onSuccess(route)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun addNewPoint(route: Route, point: Point): Single<Route> {
        return Single.create { emitter ->
            try {
                route.addPath(getPathBetweenTwoPoints(route.points.last(), point))
                route.addNewPoint(point)
                emitter.onSuccess(route)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun removePoint(route: Route, point: Point): Single<Route> {
        return Single.create<Route> {
            try {
                route.removePoint(point)
                route.deletePath()
                route.points.forEachIndexed { index, geoPoint ->
                    route.points.getOrNull(index + 1)?.let {
                        route.addPath(getPathBetweenTwoPoints(geoPoint, it))
                    }
                }
                it.onSuccess(route)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun getPathBetweenTwoPoints(start: Point, finish: Point): List<Point> {
        try {
            val serverURL =
                "https://graphhopper.com/api/1/route?point=" + start.lat + "," + start.lon + "&point=" + finish.lat + "," + finish.lon + "&vehicle=foot&index.max_region_search=8&locale=de&key=" + API_KEY + "&type=json"
            val myResponse = URL(serverURL).readText()
            val jsonResponse = JSONObject(myResponse)
            val paths = jsonResponse.getJSONArray("paths")
            val path = paths.getJSONObject(0)
            val points = path.get("points").toString()
            return decode(points)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Throwable(e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Throwable(e.localizedMessage)
        }
    }

    private fun decode(encodedPath: String): List<Point> {
        val len = encodedPath.length
        val path = mutableListOf<Point>()
        var index = 0
        var lat = 0
        var lng = 0

        while (index < len) {
            var result = 1
            var shift = 0
            var b: Int
            do {
                b = encodedPath[index++].toInt() - 63 - 1
                result += b shl shift
                shift += 5
            } while (b >= 0x1f)
            lat += if (result and 1 != 0) (result shr 1).inv() else result shr 1

            result = 1
            shift = 0
            do {
                b = encodedPath[index++].toInt() - 63 - 1
                result += b shl shift
                shift += 5
            } while (b >= 0x1f)
            lng += if (result and 1 != 0) (result shr 1).inv() else result shr 1

            path.add(Point(lat * 1e-5, lng * 1e-5))
        }

        return path
    }

    companion object {
        private const val API_KEY = "df5b853f-d498-457c-b2ab-7f157acecbea"
    }
}