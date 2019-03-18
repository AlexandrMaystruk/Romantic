package maystruks08.gmail.com.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hikes_participants")
data class HikesParticipantsTable(
    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "id_hike_firestore") var idHike: String,
    @ColumnInfo(name = "id_participant") var idParticipant: String
)
