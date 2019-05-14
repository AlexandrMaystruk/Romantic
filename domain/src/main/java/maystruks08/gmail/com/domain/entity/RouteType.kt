package maystruks08.gmail.com.domain.entity

enum class RouteType(val id: Int) {

    MAIN(0),
    SPARE(1);

    companion object {
        fun fromIndex(postIndex: Int): RouteType{
            return when(postIndex){
                0 -> MAIN
                1 -> SPARE
                else -> SPARE
            }
        }
    }


}