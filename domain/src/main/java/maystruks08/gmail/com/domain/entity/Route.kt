package maystruks08.gmail.com.domain.entity

data class Route(val id: Long, val type: RouteType, val geoPoints: MutableList<GeoPoint>)