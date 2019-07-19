package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.Point

class POJORoute {
    var id: Long = -1
    var name: String = ""
    var hikeId: Long = -1
    var routeType: Int = -1
    var points = listOf<Point>()
    var completeRoutePath: List<Point>? = null
    var image: String? = null

    constructor()

    constructor(
        id: Long,
        name: String,
        hikeId: Long,
        routeType: Int,
        points: List<Point>,
        completeRoutePath: List<Point>?,
        image: String?
    ) {
        this.id = id
        this.name = name
        this.hikeId = hikeId
        this.routeType = routeType
        this.points = points
        this.completeRoutePath = completeRoutePath
        this.image = image
    }
}