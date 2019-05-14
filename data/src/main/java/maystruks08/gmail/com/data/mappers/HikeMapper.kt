package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.POJOHike
import java.util.*
import javax.inject.Inject

class HikeMapper @Inject constructor() {

    fun toHikeTable(hike: Hike): HikeTable {
        val gSon = Gson()
        return hike.let {
            HikeTable(
                it.id,
                it.typeHike.type,
                it.dateStart.time,
                it.dateEnd.time,
                it.hikeChief,
                it.region,
                it.category.type,
                gSon.toJson(it.route)
            )
        }
    }

    fun toHike(hikePOJO: POJOHike): Hike {
        return hikePOJO.let {
            Hike(
                it.id,
                TypeHike.fromValue(it.typeHike),
                Date(it.dateStart),
                Date(it.dateEnd),
                it.hikeChief,
                it.region,
                Category.fromValue(it.category),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
            )
        }
    }

    private fun toFireBaseHike(hike: Hike): POJOHike {
        return hike.let {
            POJOHike(
                it.id,
                it.typeHike,
                it.dateStart,
                it.dateEnd,
                it.hikeChief,
                it.region,
                it.category
            )
        }
    }

    fun toFireBaseHike(hikeTable: HikeTable): POJOHike {
        return toFireBaseHike(toHikeFromTable(hikeTable))
    }

    fun toHikeTable(POJOHike: POJOHike): HikeTable {
        return toHikeTable(toHike(POJOHike))
    }

    fun toHikeTableList(hikes: List<Hike>): List<HikeTable> {
        return hikes.map { toHikeTable(it) }
    }

    fun toHikeFromTable(hikeTable: HikeTable): Hike {
        val gSon = Gson()
        return hikeTable.let {
            Hike(
                it.id,
                TypeHike.fromValue(it.type),
                Date(it.dateStart),
                Date(it.dateEnd),
                it.hikeChief,
                it.region,
                Category.fromValue(it.category),
                gSon.fromJson<MutableList<Route>>(it.route)
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}