package maystruks08.gmail.com.data.mappers

import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.domain.entity.Hike
import javax.inject.Inject

class HikeMapper @Inject constructor() {

    fun toHikeTable(hike: Hike): HikeTable{
        return hike.let {
            HikeTable(it.id,
                it.typeHike.type,
                it.dateStart.time,
                it.dateEnd.time,
                it.hikeChief,
                it.region,
                it.category.type)
        }
    }


    fun toHikeTableList(hikes : List<Hike>): List<HikeTable>{
        return hikes.map { toHikeTable(it) }
    }
}