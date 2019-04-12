package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import maystruks08.gmail.com.domain.entity.Participant

class ParticipantViewModel(val post: String? = null, val hikeId: Long, val userViewModel: UserViewModel) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readParcelable(UserViewModel::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(post)
        parcel.writeLong(hikeId)
        parcel.writeParcelable(userViewModel, flags)
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
                    it.post.name,
                    it.hikeId,
                    UserViewModel.fromUser(it.user)
                )
            }

    }
}


