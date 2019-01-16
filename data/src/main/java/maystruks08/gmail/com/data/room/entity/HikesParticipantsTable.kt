package maystruks08.gmail.com.data.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hikes_participants")
data class HikesParticipantsTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,


    @field:ColumnInfo(name = "id_hike_firestore")
    var idHike: String,
    @field:ColumnInfo(name = "id_participant")
    var idParticipant: String
)
