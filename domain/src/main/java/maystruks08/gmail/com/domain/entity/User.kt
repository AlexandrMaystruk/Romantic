package maystruks08.gmail.com.domain.entity

data class User(
    val id: String,
    val displayName: String,
    val email: String,
    val userExperienceMountain: Int,
    val userExperienceWalking: Int,
    val userExperienceWater: Int,
    val userExperienceSki: Int,
    val userPhotoUrl: String
)