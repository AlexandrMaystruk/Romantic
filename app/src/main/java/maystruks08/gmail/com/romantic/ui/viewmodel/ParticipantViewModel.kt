package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import maystruks08.gmail.com.domain.entity.Participant

class ParticipantViewModel(val post: String? = null, val hikeId: Long, id: String, displayName: String, email: String) :
    UserViewModel(id, displayName, email), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(post)
        parcel.writeLong(hikeId)
        parcel.writeString(super.id)
        parcel.writeString(super.displayName)
        parcel.writeString(super.email)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParticipantViewModel> {
        override fun createFromParcel(parcel: Parcel): ParticipantViewModel {
            return ParticipantViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ParticipantViewModel?> {
            return arrayOfNulls(size)
        }

        fun fromParticipant(participant: Participant): ParticipantViewModel =
            participant.let {
                ParticipantViewModel(
                    it.post?.name,
                    it.hikeId,
                    it.id,
                    it.displayName,
                    it.email
                )
            }

    }
}


