package maystruks08.gmail.com.data.sources.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participants")
data class ParticipantTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var displayName: String? = null,
    var email: String? = null,
    var fireBaseToken: String? = null,
    var userIdFireBase: String? = null,
    var userExperienceMountain: Int = 0,
    var userExperienceWalking: Int = 0,
    var userExperienceWater: Int = 0,
    var userExperienceSki: Int = 0,
    var userPhotoUrl: String? = null
) {
    constructor() : this(
        null, "", "", "", "", 0,
        0, 0, 0, ""
    )
}




