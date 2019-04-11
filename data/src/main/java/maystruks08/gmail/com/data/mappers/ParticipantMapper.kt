package maystruks08.gmail.com.data.mappers

import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.UserPost

class ParticipantMapper {

    fun toParticipantTable(participant: Participant): ParticipantTable {
        return participant.let {
            ParticipantTable(
                hikeId = it.hikeId,
                post = it.post?.name?:"",
                userId = it.id,
                displayName = it.displayName,
                email = it.email,
                userExperienceMountain = it.userExperienceMountain,
                userExperienceWalking = it.userExperienceWalking,
                userExperienceSki = it.userExperienceSki,
                userExperienceWater = it.userExperienceWater,
                userPhotoUrl = it.userPhotoUrl

            )
        }
    }

    fun toParticipant(participant: ParticipantTable): Participant {
        return participant.let {
            Participant(
                post = UserPost.valueOf(it.post),
                hikeId = it.hikeId,
                id = it.userId,
                displayName = it.displayName,
                email = it.email,
                userExperienceMountain = it.userExperienceMountain,
                userExperienceWalking = it.userExperienceWalking,
                userExperienceSki = it.userExperienceSki,
                userExperienceWater = it.userExperienceWater,
                userPhotoUrl = it.userPhotoUrl
            )
        }
    }
}
