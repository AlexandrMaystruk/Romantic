package maystruks08.gmail.com.data.room

import androidx.room.TypeConverter
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import java.util.*

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    @JvmStatic
    fun toTimeStamp(date: Date?): Long? = date?.let { date.time }


}