package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route")
data class RouteTable(
    @PrimaryKey
    val id: Long,
    var type: Int,
    var geoPoints: String = "",
    val upload: Long? = null,
    val updated: Int? = null
   
)




