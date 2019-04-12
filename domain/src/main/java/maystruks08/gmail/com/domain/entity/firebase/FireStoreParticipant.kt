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
    constructor() : this(UserPost.BOSS,0L, "", "", "", 0, 0, 0, 0)
}