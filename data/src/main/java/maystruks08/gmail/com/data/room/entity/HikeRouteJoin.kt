package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["hikeIdJoin", "routeIdJoin"],
    tableName = "hike_route_join", indices = [Index(value = ["hikeIdJoin", "hikeIdJoin"])]
)
data class HikeRouteJoin(
    var hikeIdJoin: Long,
    var routeIdJoin: Long
){
    fun key() = "$hikeIdJoin-$routeIdJoin"
}
