package maystruks08.gmail.com.domain.entity

import java.util.*

data class Hike(
    val id: Int,
    val typeHike: TypeHike,
    val dateStart: Date,
    val dateEnd: Date,
    val hikeChief: String,
    val region: String,
    val category: Category
)