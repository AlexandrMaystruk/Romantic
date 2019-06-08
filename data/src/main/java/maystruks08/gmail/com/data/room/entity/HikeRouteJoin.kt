package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["hikeId", "routeId"],
    tableName = "hike_route_join", indices = [Index(value = ["hikeId", "routeId"])]
)
data class HikeRouteJoin(
    var hikeId: Long,
    var routeId: Long
){
    fun key() = "$hikeId-$routeId"
}
