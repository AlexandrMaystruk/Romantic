package maystruks08.gmail.com.domain.entity

enum class TypeHike(val type: Int) {
    MOUNTAIN(0),
    SKI(1),
    WALKING(2),
    WATER(3);

    companion object {
        fun fromValue(value: Int): TypeHike {
            return when (value) {
                TypeHike.MOUNTAIN.type -> TypeHike.MOUNTAIN
                TypeHike.SKI.type -> TypeHike.SKI
                TypeHike.WALKING.type -> TypeHike.WALKING
                else -> TypeHike.WATER
            }
        }
    }
}