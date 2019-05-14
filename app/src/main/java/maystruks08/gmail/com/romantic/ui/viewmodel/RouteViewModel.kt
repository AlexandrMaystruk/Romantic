package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import maystruks08.gmail.com.domain.entity.GeoPoint
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType

data class RouteViewModel(val id: Long, val type: String, val geoPoints: MutableList<GeoPoint>) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        Gson().fromJson<MutableList<GeoPoint>>(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(type)
        parcel.writeString(Gson().toJson(geoPoints))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RouteViewModel> {
        override fun createFromParcel(parcel: Parcel): RouteViewModel {
            return RouteViewModel(parcel)
        }

        override fun newArray(size: Int): Array<RouteViewModel?> {
            return arrayOfNulls(size)
        }

        private inline fun <reified T> Gson.fromJson(json: String) =
            this.fromJson<T>(json, object : TypeToken<T>() {}.type)

        fun toRouteViewModel(route: Route): RouteViewModel {
            return route.let {
                RouteViewModel(
                    it.id,
                    it.type.name,
                    it.geoPoints
                )
            }
        }
    }

}