package maystruks08.gmail.com.domain.entity

enum class Category(val type: Int) {
    PVD(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIVE(5),
    SIXTH(6);

    companion object {
        fun fromValue(value: Int): Category {
            return when (value) {
                Category.PVD.type -> Category.PVD
                Category.FIRST.type -> Category.FIRST
                Category.SECOND.type -> Category.SECOND
                Category.THIRD.type -> Category.THIRD
                Category.FOURTH.type -> Category.FOURTH
                Category.FIVE.type -> Category.FIVE
                else -> Category.SIXTH
            }
        }
    }
}