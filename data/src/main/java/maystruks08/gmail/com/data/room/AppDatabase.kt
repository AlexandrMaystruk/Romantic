package maystruks08.gmail.com.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import maystruks08.gmail.com.data.sources.room.dao.*
import maystruks08.gmail.com.data.sources.room.entity.*

@Database(
    entities = [HikeTable::class, ParticipantTable::class,
        HikesParticipantsTable::class, HikeToolsTable::class,
        ToolsTable::class, NewsTable::class], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hikeDao(): HikeDAO

    abstract fun participantDao(): ParticipantDAO

    abstract fun hikesParticipantsDAO(): HikesParticipantsDAO

    abstract fun toolsDAO(): ToolsDAO

    abstract fun hikeToolsDAO(): HikeToolsDAO

    abstract fun newsDAO(): NewsDAO

}