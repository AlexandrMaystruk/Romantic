package maystruks08.gmail.com.domain.entity

enum class UserPost(val id: Int) {

    BOSS(1),
    MEDIC(2),
    COOK(3),
    REPAIR_KIT(4),
    CLOWN(5),
    TOOLS_CONTROLLER(6),
    MPS(7),
    PHOTOGRAPH(8);

    companion object {
        fun fromIndex(postIndex: Int): UserPost{
            return when(postIndex){
                1 -> BOSS
                2 -> MEDIC
                3 -> COOK
                4 -> REPAIR_KIT
                5 -> CLOWN
                6 -> TOOLS_CONTROLLER
                7 -> MPS
                8 -> PHOTOGRAPH
                else -> CLOWN
            }
        }
    }


}