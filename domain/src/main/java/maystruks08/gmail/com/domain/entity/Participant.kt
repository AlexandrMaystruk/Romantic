package maystruks08.gmail.com.domain.entity

class Participant(
    val post: UserPost? = null,
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
    constructor() : this(null, 0L, "", "", "", 0, 0, 0, 0)
}