package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.domain.entity.*
import maystruks08.gmail.com.domain.entity.firebase.FireStoreHike
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

    fun toHike(hikeFireStore: FireStoreHike): Hike {
        return hikeFireStore.let {
            Hike(
                it.id,
                it.typeHike,
                it.dateStart,
                it.dateEnd,
                it.hikeChief,
                it.region,
                it.category,
                Route(mutableListOf()),
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
            )
        }
    }

    private fun toFireBaseHike(hike: Hike): FireStoreHike {
        return hike.let {
            FireStoreHike(
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

    fun toFireBaseHike(hikeTable: HikeTable): FireStoreHike {
        return toFireBaseHike(toHikeFromTable(hikeTable))
    }

    fun toHikeTable(fireStoreHike: FireStoreHike): HikeTable {
        return toHikeTable(toHike(fireStoreHike))
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
                gSon.fromJson<Route>(it.route)
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}