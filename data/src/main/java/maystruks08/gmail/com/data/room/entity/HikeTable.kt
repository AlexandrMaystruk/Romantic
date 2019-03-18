package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hikes")
data class HikeTable(
    @PrimaryKey
    var id: Int,
    val type: Int,
    val dateStart: String,
    val dateEnd: String,
    val hikeChief: String,
    val region: String
)


