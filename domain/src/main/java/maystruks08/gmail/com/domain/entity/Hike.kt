package maystruks08.gmail.com.domain.entity

data class Hike(
    val id: Int,
    val typeHike: TypeHike,
    val dateOfHike: String,
    val hikeChief: String,
    val region: String,
    val category: String
)