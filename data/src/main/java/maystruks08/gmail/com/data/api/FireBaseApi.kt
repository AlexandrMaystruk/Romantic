package maystruks08.gmail.com.data.api

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import javax.inject.Inject

class FireBaseApi @Inject constructor() {

    val hikeList = listOf(
        Hike(1, TypeHike.MOUNTAIN, "23456", "adf", "reg", "cat"),
        Hike(2, TypeHike.WALKING, "345", "arg335", "bdfbs", "csfat"),
        Hike(4, TypeHike.WATER, "34jhvjh5", "arg335", "bdfbs", "csfat"),
        Hike(3, TypeHike.SKI, "3asg45", "asfgda", "345dfgsdgbs", "csfat")
    )

}