package maystruks08.gmail.com.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import io.reactivex.Single
import maystruks08.gmail.com.domain.repository.RouteRepository
import org.json.JSONException
import org.json.JSONObject
import org.osmdroid.util.GeoPoint
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor() : RouteRepository {


    companion object {

        private const val API_KEY = "3cf98933-427c-458c-87c0-3009ac182044"

    }


    private fun getPathBetweenTwoPoints(start: GeoPoint, finish: GeoPoint): Single<ArrayList<GeoPoint>> {
        val serverURL =
            "https://graphhopper.com/api/1/route?point=" + start.latitude + "," + start.longitude + "&point=" + finish.latitude + "," + finish.longitude + "&vehicle=foot&locale=de&key=" + API_KEY

        val url = URL(serverURL)
        val connection = url.openConnection() as HttpURLConnection
        connection.doOutput = true
        connection.requestMethod = "get"

        try {
            return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val myResponse = reader.readLine()
                val jsonResponse = JSONObject(myResponse)
                val paths = jsonResponse.getJSONArray("paths")
                val path = paths.getJSONObject(0)
                val points = path.get("points").toString()
                Single.just(decode(points))

            } else {
                Log.d(TAG, BufferedReader(InputStreamReader(connection.errorStream)).readLine())
                Single.error(Throwable(BufferedReader(InputStreamReader(connection.errorStream)).readLine()))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
            return Single.error(Throwable(e.localizedMessage))
        } catch (e: Exception) {
            e.printStackTrace()
            return Single.error(Throwable(e.localizedMessage))
        } finally {
            connection.disconnect()
        }
    }

    private fun decode(encodedPath: String): ArrayList<GeoPoint> {

        val len = encodedPath.length

        val path = ArrayList<GeoPoint>(len / 2)
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

            path.add(GeoPoint(lat * 1e-5, lng * 1e-5))
        }

        return path
    }
}