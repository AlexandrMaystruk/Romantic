package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.Point

class POJORoute {
    var id: Long = -1
    var routeType: Int = -1
    var route: List<Point> = listOf()

    constructor()

    constructor(
        id: Long,
        routeType: Int,
        route: List<Point>
    ) {
        this.id = id
        this.routeType = routeType
        this.route = route
    }
}