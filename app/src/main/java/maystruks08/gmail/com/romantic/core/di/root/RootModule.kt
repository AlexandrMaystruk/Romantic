package maystruks08.gmail.com.romantic.core.di.root

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.HikeDAO

@Module
class RootModule {

    @Provides
    @RootScope
    fun closeDayDao(appDatabase: AppDatabase): HikeDAO = appDatabase.hikeDao()


}