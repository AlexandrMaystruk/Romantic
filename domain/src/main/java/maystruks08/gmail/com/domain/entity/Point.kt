package maystruks08.gmail.com.domain.entity

import java.lang.StringBuilder

class Point {
    var id: String = ""
    var number: Int = 1
    var lat: Double = 0.0
    var lon: Double = 0.0

    constructor()

    constructor(lat: Double, lon: Double) {
        this.lat = lat
        this.lon = lon
    }

    constructor(id: String, lat: Double, lon: Double
    ) {
        this.id = id
        this.lat = lat
        this.lon = lon
    }

    constructor(id: String, number: Int, lat: Double, lon: Double
    ) {
        this.id = id
        this.number = number
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