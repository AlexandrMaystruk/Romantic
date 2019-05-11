package maystruks08.gmail.com.domain.entity

open class User(
    val id: String ="",
    val displayName: String ="",
    val email: String = "",
    val userExperienceMountain: Int = 0,
    val userExperienceWalking: Int = 0,
    val userExperienceWater: Int = 0,
    val userExperienceSki: Int = 0,
    val userPhotoUrl: String? = null
) {
    constructor() : this("", "", "")
}