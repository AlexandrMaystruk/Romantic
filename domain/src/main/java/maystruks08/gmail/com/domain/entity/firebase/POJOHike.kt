package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.Category
import maystruks08.gmail.com.domain.entity.TypeHike
import java.util.*

class POJOHike {
    var id: Long = 0
    var typeHike: Int = 0
    var dateStart: Long = 0
    var dateEnd: Long = 0
    var hikeChief: String = ""
    var region: String = ""
    var category: Int = 0

    constructor()

    constructor(
        id: Long,
        typeHike: TypeHike,
        dateStart: Date,
        dateEnd: Date,
        hikeChief: String,
        region: String,
        category: Category
    ) {
        this.id = id
        this.typeHike = typeHike.type
        this.dateStart = dateStart.time
        this.dateEnd = dateEnd.time
        this.hikeChief = hikeChief
        this.region = region
        this.category = category.type
    }
}