package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.GeoPoint

class POJORoute {
    var id: Long = -1
    var routeType: Int = -1
    var route: List<GeoPoint> = listOf()

    constructor()

    constructor(
        id: Long,
        routeType: Int,
        route: List<GeoPoint>
    ) {
        this.id = id
        this.routeType = routeType
        this.route = route
    }
}