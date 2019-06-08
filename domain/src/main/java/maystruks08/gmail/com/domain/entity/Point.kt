package maystruks08.gmail.com.domain.entity

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
}