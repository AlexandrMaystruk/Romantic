package maystruks08.gmail.com.romantic.core.di.hike

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO

@Module
class HikeModule {

    @Provides
    @HikeScope
    fun closeDayDao(appDatabase: AppDatabase): HikeDAO = appDatabase.hikeDao()


}