package maystruks08.gmail.com.domain.entity

enum class TypeHike(private val type: Int) {
    MOUNTAIN(0),
    WATER(1),
    SKI(2),
    WALKING(3);

    companion object {
        fun fromValue(value: Int): TypeHike {
            return when (value) {
                TypeHike.MOUNTAIN.type -> TypeHike.MOUNTAIN
                TypeHike.WATER.type -> TypeHike.WATER
                TypeHike.SKI.type -> TypeHike.SKI
                else -> TypeHike.WATER
            }
        }
    }
}