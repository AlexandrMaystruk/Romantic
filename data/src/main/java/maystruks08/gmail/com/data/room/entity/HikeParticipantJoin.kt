package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["hikeId", "participantId"],
    tableName = "hike_participant_join", indices = [Index(value = ["hikeId", "participantId"])]
)
data class HikeParticipantJoin(
    var hikeId: Long,
    var participantId: String
){
    fun key() = "$hikeId-$participantId"
}
