package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "hike_tool_join",
    primaryKeys = ["hikeId", "toolId"],
    foreignKeys = [
        ForeignKey(
            entity = HikeTable::class,
            parentColumns = ["id"],
            childColumns = ["hikeId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ToolsTable::class,
            parentColumns = ["id"],
            childColumns = ["toolId"],
            onDelete = CASCADE
        )
    ]
)
data class HikeToolJoin(
    var hikeId: Long,
    var toolId: Long
)
