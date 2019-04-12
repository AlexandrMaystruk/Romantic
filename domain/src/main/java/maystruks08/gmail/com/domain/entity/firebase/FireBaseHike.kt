package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.Category
import maystruks08.gmail.com.domain.entity.TypeHike
import java.util.*

data class FireBaseHike(
    val id: Long,
    val typeHike: TypeHike,
    val dateStart: Date,
    val dateEnd: Date,
    val hikeChief: String,
    val region: String,
    val category: Category
){
    constructor() : this(0L, TypeHike.MOUNTAIN, Date(), Date(), "", "", Category.PVD)
}