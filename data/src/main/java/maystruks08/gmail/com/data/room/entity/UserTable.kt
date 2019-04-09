package maystruks08.gmail.com.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserTable(
    @PrimaryKey
    var id: String = "",
    var displayName: String = "",
    var email: String = "",
    var userExperienceMountain: Int = 0,
    var userExperienceWalking: Int = 0,
    var userExperienceWater: Int = 0,
    var userExperienceSki: Int = 0,
    var userPhotoUrl: String? = null
)




