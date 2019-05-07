package maystruks08.gmail.com.domain.entity.firebase

import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost

class FireStoreParticipant(
    val post: UserPost,
    val hikeId: Long,
    id: String,
    displayName: String,
    email: String,
    userExperienceMountain: Int,
    userExperienceWalking: Int,
    userExperienceWater: Int,
    userExperienceSki: Int,
    userPhotoUrl: String? = null
) : User(
    id,
    displayName,
    email,
    userExperienceMountain,
    userExperienceWalking,
    userExperienceWater,
    userExperienceSki,
    userPhotoUrl
) {
    constructor() : this(UserPost.BOSS, -1L, "", "", "", 0, 0, 0, 0, "")

    fun toMap(): Map<String, String> =
        mapOf(
            "post" to post.name,
            "hikeId" to hikeId.toString(),
            "id" to id,
            "displayName" to displayName,
            "email" to email,
            "userExperienceMountain" to userExperienceMountain.toString(),
            "userExperienceWalking" to userExperienceWalking.toString(),
            "userExperienceWater" to userExperienceWater.toString(),
            "userExperienceSki" to userExperienceSki.toString(),
            "userPhotoUrl" to (userPhotoUrl ?: "")
        )
}