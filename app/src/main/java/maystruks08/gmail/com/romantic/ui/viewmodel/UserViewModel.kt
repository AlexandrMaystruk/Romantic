package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import maystruks08.gmail.com.domain.entity.User

data class UserViewModel(
    val id: String,
    val displayName: String,
    val email: String,
    val userExperienceMountain: Int = 0,
    val userExperienceWalking: Int = 0,
    val userExperienceWater: Int = 0,
    val userExperienceSki: Int = 0,
    val userPhotoUrl: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(displayName)
        parcel.writeString(email)
        parcel.writeInt(userExperienceMountain)
        parcel.writeInt(userExperienceWalking)
        parcel.writeInt(userExperienceWater)
        parcel.writeInt(userExperienceSki)
        parcel.writeString(userPhotoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserViewModel> {
        override fun createFromParcel(parcel: Parcel): UserViewModel {
            return UserViewModel(parcel)
        }

        override fun newArray(size: Int): Array<UserViewModel?> {
            return arrayOfNulls(size)
        }

        fun fromUser(user: User): UserViewModel =
            user.let {
                UserViewModel(
                    it.id,
                    it.displayName,
                    it.email,
                    it.userExperienceMountain,
                    it.userExperienceWalking,
                    it.userExperienceWater,
                    it.userExperienceSki,
                    it.userPhotoUrl
                )
            }
    }
}