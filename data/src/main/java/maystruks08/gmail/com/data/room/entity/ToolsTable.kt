package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tools")
data class ToolsTable(
    @PrimaryKey
    var id: Long,
    var description: String?,
    var count: String?,
    var pictureURL: String?
)
