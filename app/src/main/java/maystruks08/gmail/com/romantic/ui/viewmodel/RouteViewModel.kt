package maystruks08.gmail.com.romantic.ui.viewmodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import maystruks08.gmail.com.data.fromJson
import maystruks08.gmail.com.data.toJsonOrNull
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.RouteType

data class RouteViewModel(
    val id: Long,
    val name: String,
    val hikeId: Long,
    var type: RouteType,
    val points: MutableList<Point>,
    var completeRoutePath: MutableList<Point>?
) : Parcelable {

    private val gson = Gson()

    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        hikeId = parcel.readLong(),
        name = parcel.readString()?:"",
        type = Gson().fromJson(parcel.readString() ?: ""),
        points = Gson().fromJson<MutableList<Point>>(parcel.readString() ?: ""),
        completeRoutePath = Gson().fromJson<MutableList<Point>>(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(hikeId)
        parcel.writeString(name)
        parcel.writeString(gson.toJson(type))
        parcel.writeString(gson.toJson(points))
        parcel.writeString(gson.toJsonOrNull(completeRoutePath))
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

        fun toRouteViewModel(route: Route): RouteViewModel {
            return route.let {
                RouteViewModel(
                    it.id,
                    it.name,
                    it.hikeId,
                    it.type,
                    it.points,
                    it.completeRoutePath
                )
            }
        }
    }
}