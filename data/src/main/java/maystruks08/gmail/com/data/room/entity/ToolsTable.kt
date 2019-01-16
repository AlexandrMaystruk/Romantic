package maystruks08.gmail.com.data.sources.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tools")
data class ToolsTable(

    @NonNull
    @PrimaryKey var id: String = "",

    @ColumnInfo(name = "hike_id")
    var hikeId: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "count")
    var count: String? = null,

    @ColumnInfo(name = "picture_url")
    var pictureURL: String? = null
)
