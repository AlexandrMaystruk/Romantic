package maystruks08.gmail.com.data.api

import maystruks08.gmail.com.domain.entity.Category
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import java.util.*
import javax.inject.Inject

class FireBaseApi @Inject constructor() {

    private val hikeList = listOf(
        Hike(1, TypeHike.MOUNTAIN, Date(), Date(), "reg", "cat", Category.FIRST),
        Hike(1, TypeHike.MOUNTAIN, Date(), Date(), "reswgg", "cadfgt", Category.SECOND),
        Hike(2, TypeHike.WALKING, Date(), Date(), "bdfbs", "csfat", Category.FIRST),
        Hike(3, TypeHike.WATER, Date(), Date(), "bdfbs", "csfat", Category.FIRST),
        Hike(4, TypeHike.SKI, Date(), Date(), "345dfgsdgbs", "csfat", Category.FIRST),
        Hike(456, TypeHike.WATER, Date(), Date(), "bdfbs", "csfat", Category.FIRST),
        Hike(63, TypeHike.SKI, Date(), Date(), "345dfgsdgbs", "csfat", Category.FIRST)
    )


    fun getAllHikes(): List<Hike> {
        return hikeList
    }

}