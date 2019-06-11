package maystruks08.gmail.com.data

import maystruks08.gmail.com.domain.entity.Point
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Will perform synchronous request
 * Example:
 *      RESTRequest.Builder()
 *          .requestType(RequestType.GET)
 *          .endpointURL("https://google.com/")
 *          .requestProperty("Content-Type", "application/x-www-form-urlencoded")
 *          .requestProperty("x-api-key", "value")
 *          .bodyParameter("key1", "value1")
 *          .bodyParameter("key2", "value2")
 *          .doOutput(true)
 *          .build().perform(object : RESTResponseCallback {
 *              override fun onResponse(responseCode: Int, result: String) {
 *                  //action
 *              }
 *              override fun onError(e: Throwable) {
 *                  e.printStackTrace()
 *                  throw java.lang.Exception(e.message)
 *              }
 *          })
 */

class RESTRequest private constructor(
    private val requestType: RequestType, private val endpointURL: String?,
    private val reqProperties: HashMap<String, String>,
    private val bodyParameters: HashMap<String, String>,
    private val outputNeeded: Boolean, var responseCallback: RESTResponseCallback?) {

    fun perform(respCallback: RESTResponseCallback) {
        this.responseCallback = respCallback
        var body: String? = null
        if (!bodyParameters.isNullOrEmpty()) {
            body = getBodyQuery(bodyParameters)
        }
        val connection = (URL(endpointURL).openConnection() as HttpURLConnection).apply {
            this.doOutput = outputNeeded
            requestMethod = requestType.asText
            if (!reqProperties.isNullOrEmpty()) {
                for ((key, value) in reqProperties) {
                    setRequestProperty(key, value)
                    body?.let {
                        setRequestProperty("Content-Length", body.toByteArray().size.toString())
                    }
                }
            }
        }
        BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8")).write(body)
        try {
            val outputStream = OutputStreamWriter(connection.outputStream)
            outputStream.write(body)
            outputStream.flush()

            val stream = if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream
            } else {
                connection.errorStream
            }
            val result = StringBuilder()
            if (outputNeeded) {
                val buffer = BufferedReader(InputStreamReader(stream))
                var line = buffer.readLine()
                while (!line.isNullOrEmpty()) {
                    result.append(line)
                    line = buffer.readLine()
                }
            }
            responseCallback?.onResponse(connection.responseCode, result.toString())
        } catch (e: Exception) {
            responseCallback?.onError(e)
        } finally {
            connection.disconnect()
        }
    }

    /**
     * @param requestType
     * @param endpointURL
     * @param requestProperties you can add one by one, all at once, or use both ability
     * @param bodyParameters you can add one by one, all at once, or use both ability
     * @param setDoOutput {@link java.net.URLConnection#getDoOutput()}
     * @param responseCallback if needed
     */
    class Builder(var requestType: RequestType = RequestType.GET,
                  var endpointURL: String? = null,
                  var requestProperties: HashMap<String, String> = HashMap(),
                  var bodyParameters: HashMap<String, String> = HashMap(),
                  var setDoOutput: Boolean = false,
                  var responseCallback: RESTResponseCallback? = null) {

        fun requestType(requestType: RequestType) = apply {
            this.requestType = requestType
        }

        fun endpointURL(endpointURL: String?) = apply {
            this.endpointURL = endpointURL
        }

        fun requestProperty(key: String, value: String) = apply {
            requestProperties[key] = value
        }

        fun requestProperties(requestProperties: MutableList<Pair<String, String>>) = apply {
            this.requestProperties.putAll(requestProperties)
        }

        fun bodyParameter(key: String, value: String) = apply {
            bodyParameters[key] = value
        }

        fun bodyParameters(bodyParameters: MutableList<Pair<String, String>>) = apply {
            this.bodyParameters.putAll(bodyParameters)
        }

        fun doOutput(doOutput: Boolean) = apply {
            this.setDoOutput = doOutput
        }

        fun build() = RESTRequest(requestType, endpointURL, requestProperties, bodyParameters,
                setDoOutput, responseCallback)
    }

    private fun getBodyQuery(params: HashMap<String, String>): String {
        val result = StringBuilder()
        var first = true

        for ((key, value) in params) {
            if (first) {
                first = false
            } else {
                result.append("&")
            }
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
        }
        return result.toString()
    }
}


enum class RequestType(val asText: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE")
}

interface RESTResponseCallback {
    fun onResponse(responseCode: Int, output: String) : List<Point>
    fun onError(e: Throwable)
}