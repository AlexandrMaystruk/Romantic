package maystruks08.gmail.com.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.domain.entity.Category
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.firebase.FireBaseHike
import java.util.*
import javax.inject.Inject

class HikeMapper @Inject constructor() {

    fun toHikeTable(hike: Hike): HikeTable {
        return hike.let {
            HikeTable(
                it.id,
                it.typeHike.type,
                it.dateStart.time,
                it.dateEnd.time,
                it.hikeChief,
                it.region,
                it.category.type,
                Gson().toJson(it.group)
            )
        }
    }

    fun toHike(hikeFirestore: FireBaseHike): Hike {
        return hikeFirestore.let {
            Hike(
                it.id,
                it.typeHike,
                it.dateStart,
                it.dateEnd,
                it.hikeChief,
                it.region,
                it.category,
                mutableListOf()
            )
        }
    }

    private fun toFireBaseHike(hike: Hike): FireBaseHike {
        return hike.let {
            FireBaseHike(
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

    fun toFireBaseHike(hikeTable: HikeTable): FireBaseHike {
        return toFireBaseHike(toHikeFromTable(hikeTable))
    }

    fun toHikeTableList(hikes: List<Hike>): List<HikeTable> {
        return hikes.map { toHikeTable(it) }
    }

    fun toHikeFromTable(hikeTable: HikeTable): Hike {
        return hikeTable.let {
            Hike(
                it.id,
                TypeHike.fromValue(it.type),
                Date(it.dateStart),
                Date(it.dateEnd),
                it.hikeChief,
                it.region,
                Category.fromValue(it.category),
                Gson().fromJson<MutableList<Participant>>(it.group)
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}