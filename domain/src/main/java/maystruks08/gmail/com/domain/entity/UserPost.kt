package maystruks08.gmail.com.domain.entity

enum class UserPost {

    BOSS,
    MEDIC,
    COOK,
    REPAIR_KIT,
    CLOWN,
    TOOLS_CONTROLLER,
    MPS,
    PHOTOGRAPH;

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