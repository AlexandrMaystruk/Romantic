package maystruks08.gmail.com.domain.entity

import java.util.*

data class Hike(
    val id: Long,
    val typeHike: TypeHike,
    val dateStart: Date,
    val dateEnd: Date,
    val hikeChief: String,
    val region: String,
    val category: Category,
    val group: MutableList<Participant>
) {

    fun addParticipantToGroup(participant: Participant){
        group.add(participant)
    }


    fun removeParticipantFromGroup(participant: Participant){
        group.remove(participant)
    }

    fun updateParticipant(participant: Participant){
        val item = group.firstOrNull { it.user.id == participant.user.id }
        if (item != null) {
            group.remove(item)
        }
        group.add(participant)
    }

}