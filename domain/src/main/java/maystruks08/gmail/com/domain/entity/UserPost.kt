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
                1 -> UserPost.BOSS
                2 -> UserPost.MEDIC
                3 -> UserPost.COOK
                4 -> UserPost.REPAIR_KIT
                5 -> UserPost.CLOWN
                6 -> UserPost.TOOLS_CONTROLLER
                7 -> UserPost.MPS
                8 -> UserPost.PHOTOGRAPH
                else -> CLOWN
            }
        }
    }


}