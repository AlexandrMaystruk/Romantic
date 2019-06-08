package maystruks08.gmail.com.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import maystruks08.gmail.com.data.room.dao.*
import maystruks08.gmail.com.data.room.entity.*

@Database(
    entities = [HikeTable::class,
        ParticipantTable::class,
        UserTable::class,
        NewsTable::class,
        ToolsTable::class,
        HikeParticipantJoin::class,
        HikeToolJoin::class,
        RouteTable::class,
        HikeRouteJoin::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hikeDao(): HikeDAO

    abstract fun userDao(): UserDAO

    abstract fun participantsDAO(): ParticipantDAO

    abstract fun toolsDAO(): ToolsDAO

    abstract fun newsDAO(): NewsDAO

}