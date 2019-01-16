package maystruks08.gmail.com.data.room.entity


import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hikes")
data class HikeTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "hike_type")
    var typeHike: Int = 0,

    @ColumnInfo(name = "id_in_firestore")
    var firestoreId: String? = null,

    @ColumnInfo(name = "id_firebase")
    var idFirebase: String? = null,

    @ColumnInfo(name = "hike_region")
    var region: String? = null,

    @ColumnInfo(name = "hike_category")
    var category: String? = null,

    @ColumnInfo(name = "hike_name")
    var hikeChief: String? = null,


    @ColumnInfo(name = "hike_date")
    var dateOfHike: String? = null
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {

        Log.d("HikeTable", "writeToParcel")
        dest.writeInt(id)
        dest.writeString(idFirebase)
        dest.writeInt(typeHike)
        dest.writeString(hikeChief)
        dest.writeString(category)
        dest.writeString(dateOfHike)
        dest.writeString(region)
        dest.writeString(firestoreId)

    }

    companion object CREATOR : Parcelable.Creator<HikeTable> {
        override fun createFromParcel(parcel: Parcel): HikeTable {
            return HikeTable(parcel)
        }

        override fun newArray(size: Int): Array<HikeTable?> {
            return arrayOfNulls(size)
        }
    }


}


