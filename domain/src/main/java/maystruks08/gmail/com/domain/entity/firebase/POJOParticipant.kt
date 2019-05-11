package maystruks08.gmail.com.domain.entity.firebase

class POJOParticipant {
    var post: Int = 0
    var hikeId: Long = 0
    var id: String = ""
    var displayName: String = ""
    var email: String = ""
    var userExperienceMountain: Int = 0
    var userExperienceWalking: Int = 0
    var userExperienceWater: Int = 0
    var userExperienceSki: Int = 0
    var userPhotoUrl: String = ""

    constructor()

    constructor(
        post: Int, hikeId: Long, id: String, displayName: String, email: String, userExperienceMountain: Int,
        userExperienceWalking: Int, userExperienceWater: Int, userExperienceSki: Int, userPhotoUrl: String
    ) {
        this.post = post
        this.hikeId = hikeId
        this.id = id
        this.displayName = displayName
        this.email = email
        this.userExperienceMountain = userExperienceMountain
        this.userExperienceWalking = userExperienceWalking
        this.userExperienceWater = userExperienceWater
        this.userExperienceSki = userExperienceSki
        this.userPhotoUrl = userPhotoUrl
    }

    companion object {

        fun fromMap(map: Map<*, *>): POJOParticipant {
            return map.let {
                POJOParticipant(
                    it["post"].toString().toInt(),
                    it["hikeId"].toString().toLong(),
                    it["id"].toString(),
                    it["displayName"].toString(),
                    it["email"].toString(),
                    it["userExperienceMountain"].toString().toInt(),
                    it["userExperienceWalking"].toString().toInt(),
                    it["userExperienceWater"].toString().toInt(),
                    it["userExperienceSki"].toString().toInt(),
                    it["userPhotoUrl"].toString()
                )
            }
        }
    }
}

