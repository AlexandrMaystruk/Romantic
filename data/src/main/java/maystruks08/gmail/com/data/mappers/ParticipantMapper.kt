package maystruks08.gmail.com.data.mappers

import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.entity.firebase.POJOParticipant
import javax.inject.Inject

class ParticipantMapper @Inject constructor() {

    fun toParticipantTable(participant: Participant): ParticipantTable {
        return participant.let {
            ParticipantTable(
                id = it.user.id,
                hikeId = it.hikeId,
                post = it.post.name,
                displayName = it.user.displayName,
                email = it.user.email,
                userExperienceMountain = it.user.userExperienceMountain,
                userExperienceWalking = it.user.userExperienceWalking,
                userExperienceSki = it.user.userExperienceSki,
                userExperienceWater = it.user.userExperienceWater,
                userPhotoUrl = it.user.userPhotoUrl
            )
        }
    }

    fun toParticipant(participant: ParticipantTable): Participant {
        return participant.let {
            Participant(
                post = UserPost.valueOf(it.post),
                hikeId = it.hikeId,
                user = User(
                    it.id,
                    it.displayName,
                    it.email,
                    it.userExperienceMountain,
                    it.userExperienceWalking,
                    it.userExperienceSki,
                    it.userExperienceWater,
                    it.userPhotoUrl
                )
            )
        }
    }

    fun toFireStoreParticipant(participant: ParticipantTable): POJOParticipant {
        return toFireStoreParticipant(toParticipant(participant))
    }


    fun toParticipant(participant: POJOParticipant): Participant {
        return participant.let {
            Participant(
                post = UserPost.fromIndex(it.post),
                hikeId = it.hikeId,
                user = User(
                    it.id,
                    it.displayName,
                    it.email,
                    it.userExperienceMountain,
                    it.userExperienceWalking,
                    it.userExperienceSki,
                    it.userExperienceWater,
                    it.userPhotoUrl
                )
            )
        }
    }


    fun toFireStoreParticipant(participant: Participant): POJOParticipant {
        return participant.let {
            POJOParticipant(
                post = it.post.ordinal,
                hikeId = it.hikeId,
                id = it.user.id,
                displayName = it.user.displayName,
                email = it.user.email,
                userExperienceMountain = it.user.userExperienceMountain,
                userExperienceWalking = it.user.userExperienceWalking,
                userExperienceSki = it.user.userExperienceSki,
                userExperienceWater = it.user.userExperienceWater,
                userPhotoUrl = it.user.userPhotoUrl ?: ""
            )
        }
    }


    fun toFireStoreParticipant(user: User, hikeId: Long, post: UserPost): POJOParticipant {
        return POJOParticipant(
            post = post.ordinal,
            hikeId = hikeId,
            id = user.id,
            displayName = user.displayName,
            email = user.email,
            userExperienceMountain = user.userExperienceMountain,
            userExperienceWalking = user.userExperienceWalking,
            userExperienceSki = user.userExperienceSki,
            userExperienceWater = user.userExperienceWater,
            userPhotoUrl = user.userPhotoUrl ?: ""
        )
    }



    fun toParticipantTableList(participants: List<Participant>): List<ParticipantTable> {
        return participants.map { toParticipantTable(it) }
    }

    fun toParticipantPOJOList(participants: List<ParticipantTable>): List<POJOParticipant> {
        return participants.map { toFireStoreParticipant(it) }
    }
}
