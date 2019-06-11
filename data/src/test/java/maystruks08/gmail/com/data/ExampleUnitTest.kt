package maystruks08.gmail.com.data

import maystruks08.gmail.com.domain.entity.Point
import org.json.JSONException
import org.junit.Test

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
    fun mustToGetPAthBetweenTwoPointsP() {
        val start = Point(39.9327534507, -31.588053451)
        val end = Point(-50.3403454, 11.64534705)

        val result = getPathBetweenTwoPoints(start, end)
        println(result)
    }


    private fun getPathBetweenTwoPoints(start: Point, finish: Point) {

        val apiKey = "df5b853f-d498-457c-b2ab-7f157acecbea"
        val serverURL = "https://graphhopper.com/api/1/route?point=" + start.lat + "," + start.lon + "&point=" + finish.lat + "," + finish.lon + "&vehicle=foot&index.max_region_search=8&locale=de&key=" + apiKey + "&type=json"

        val url = URL(serverURL)
        val connection = url.openConnection() as HttpURLConnection
        connection.doOutput = true
        connection.requestMethod = "GET"

        try {
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val myResponse = reader.readLine()
                println(myResponse)

            } else {
                throw Throwable(BufferedReader(InputStreamReader(connection.errorStream)).readLine())
            }

        } catch (e: JSONException) {
            e.printStackTrace()
            throw Throwable(e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Throwable(e.localizedMessage)
        } finally {
            connection.disconnect()
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

    enum class RequestType(val asText: String) {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        PATCH("PATCH"),
        DELETE("DELETE")
    }

}