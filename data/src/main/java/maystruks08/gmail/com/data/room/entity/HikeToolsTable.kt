package maystruks08.gmail.com.data.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "hike_tools")
data class HikeToolsTable(

    @PrimaryKey(autoGenerate = true)
    var idRoom: Int = 0,

    @ColumnInfo(name = "id")
    var id: String? = null,

    @ColumnInfo(name = "hike_id")
    var hikeId: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "count")
    var count: Int = 0,

    @ColumnInfo(name = "picture_url")
    var pictureURL: String? = null
)
