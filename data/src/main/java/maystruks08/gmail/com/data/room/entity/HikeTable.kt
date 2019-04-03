package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hikes")
data class HikeTable(
    @PrimaryKey
    var id: Long,
    val type: Int,
    val dateStart: Long,
    val dateEnd: Long,
    val hikeChief: String,
    val region: String,
    val category: Int
)


