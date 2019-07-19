package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteTable(
    @PrimaryKey
    val id: Long,
    val name: String,
    val hikeId: Long,
    var type: Int,
    val points: String,
    var completeRoutePath: String? = null,
    val image: String? = null,
    val upload: Long? = null,
    val updated: Int? = null
)




