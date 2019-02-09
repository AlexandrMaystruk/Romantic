package maystruks08.gmail.com.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import maystruks08.gmail.com.data.room.dao.*
import maystruks08.gmail.com.data.room.entity.*

@Database(
    entities = [HikeTable::class, UserTable::class,
        HikesParticipantsTable::class, HikeToolsTable::class,
        ToolsTable::class, NewsTable::class], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hikeDao(): HikeDAO

    abstract fun userDao(): UserDAO

    abstract fun hikesParticipantsDAO(): HikesParticipantsDAO

    abstract fun toolsDAO(): ToolsDAO

    abstract fun hikeToolsDAO(): HikeToolsDAO

    abstract fun newsDAO(): NewsDAO

}