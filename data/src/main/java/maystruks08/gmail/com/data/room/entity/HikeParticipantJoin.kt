package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "hike_participant_join", indices = [Index(value = ["hikeId", "participantId"])]
)
data class HikeParticipantJoin(
    @PrimaryKey(autoGenerate = true)
    var localId: Long = 0L,
    var hikeId: Long,
    var participantId: String
){
    fun key() = "$hikeId-$participantId"
}
