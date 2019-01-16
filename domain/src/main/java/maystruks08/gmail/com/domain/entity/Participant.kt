package maystruks08.gmail.com.domain.entity

data class Participant(
    val id: Int,
    val displayName: String,
    val email: String,
    val fireBaseToken: String,
    val userIdFireBase: String,
    val userExperienceMountain: Int = 0,
    val userExperienceWalking: Int = 0,
    val userExperienceWater: Int = 0,
    val userExperienceSki: Int = 0,
    val userPhotoUrl: String
)