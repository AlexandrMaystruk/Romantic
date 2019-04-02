package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import maystruks08.gmail.com.domain.entity.Hike

data class HikeViewModel(val id: Int,
                         val typeHike: Int,
                         val dateOfHike: String,
                         val hikeChief: String,
                         val region: String,
                         val category: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(typeHike)
        parcel.writeString(dateOfHike)
        parcel.writeString(hikeChief)
        parcel.writeString(region)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HikeViewModel> {
        override fun createFromParcel(parcel: Parcel): HikeViewModel {
            return HikeViewModel(parcel)
        }

        override fun newArray(size: Int): Array<HikeViewModel?> {
            return arrayOfNulls(size)
        }

        fun fromHike(hike: Hike): HikeViewModel =
            hike.let {
                HikeViewModel(
                    it.id,
                    it.typeHike.type,
                    it.dateStart.toString(),
                    it.hikeChief,
                    it.region,
                    it.category.name
                )
            }
    }

}