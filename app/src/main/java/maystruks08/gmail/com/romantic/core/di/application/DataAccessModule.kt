package maystruks08.gmail.com.romantic.core.di.application

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.romantic.core.executors.BaseExecutor
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import javax.inject.Singleton

@Module
class DataAccessModule {

    @Provides
    @Singleton
    fun executor(): ThreadExecutor = BaseExecutor()

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "sc_db").build()

    @Provides
    @Singleton
    fun fireBaseInstance(): FirebaseAuth =
        FirebaseAuth.getInstance()
}