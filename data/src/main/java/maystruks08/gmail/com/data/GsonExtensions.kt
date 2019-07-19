package maystruks08.gmail.com.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.GsonBuilder


fun getGsonInstanceWithTypeAdapter(): Gson {
    return GsonBuilder()
        .create()
}

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJsonOrNull(json: String?): T? {
    return if (json.isNullOrEmpty()) {
        null
    } else {
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    }
}

inline fun <reified T> Gson.toJsonOrNull(clazz: T?): String? {
    return if (clazz == null) {
        null
    } else {
        this.toJson(clazz)
    }
}

