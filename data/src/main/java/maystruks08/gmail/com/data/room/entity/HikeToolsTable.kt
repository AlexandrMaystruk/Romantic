package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hike_tools")
data class HikeToolsTable(
    @PrimaryKey
    var id: String,
    var hikeId: String,
    var description: String,
    var count: Int,
    var pictureURL: String? = null
)
