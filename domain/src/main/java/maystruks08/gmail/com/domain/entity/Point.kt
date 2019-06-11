package maystruks08.gmail.com.domain.entity

import java.lang.StringBuilder

class Point {
    var lat: Double = 0.0
    var lon: Double = 0.0

    constructor()

    constructor(
        lat: Double,
        lon: Double
    ) {
        this.lat = lat
        this.lon = lon
    }

    override fun toString(): String {
        return StringBuilder("Lat: ")
            .append(lat.toInt())
            .append("Log:")
            .append(lon.toInt())
            .toString()
    }
}