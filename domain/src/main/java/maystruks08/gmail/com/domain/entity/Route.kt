package maystruks08.gmail.com.domain.entity

data class Route(val id: Long, var type: RouteType, val points: MutableList<Point>, var completeRoutePath: MutableList<Point>? = null){


    fun addNewPoint(point: Point){
        points.add(point)
    }


    fun removePoint(point: Point){
        points.remove(point)
    }


    fun addPath(builtPath: List<Point>){
        completeRoutePath?.addAll(builtPath)
    }

    fun deletePath(){
        completeRoutePath = null
    }

    fun changeRouteType(type: RouteType){
        this.type = type
    }
}