package maystruks08.gmail.com.romantic.ui.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.DocumentChange
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.firebase.FireStoreHike
import javax.inject.Inject

class RefreshCacheWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @Inject
    lateinit var api: FireStoreApi

    @Inject
    lateinit var hikeDAO: HikeDAO
    @Inject
    lateinit var hikeMapper: HikeMapper

    @Inject
    lateinit var userDAO: UserDAO

    @Inject
    lateinit var userMapper: UserMapper

    @Inject
    lateinit var participantDAO: ParticipantDAO

    @Inject
    lateinit var participantMapper: ParticipantMapper

    override fun doWork(): Result {

//        api.setUpdateListener(::hikesUpdated, ::usersUpdated)

        return Result.RETRY
    }


    private fun hikesUpdated(changes: List<DocumentChange>) {
        changes.forEach {
            when {
                it.type == DocumentChange.Type.ADDED -> {
                    val newHike = it.document.toObject(FireStoreHike::class.java)
                    hikeDAO.insert(hikeMapper.toHikeTable(newHike))
                }
                it.type == DocumentChange.Type.MODIFIED -> {
                    val updatedHike = it.document.toObject(FireStoreHike::class.java)
                    hikeDAO.update(hikeMapper.toHikeTable(updatedHike))

                }
                it.type == DocumentChange.Type.REMOVED -> {
                    val deletedHike = it.document.toObject(FireStoreHike::class.java)
                    hikeDAO.delete(hikeMapper.toHikeTable(deletedHike))
                }
            }
        }
    }

    private fun usersUpdated(changes: List<DocumentChange>) {
        changes.forEach {
            when {
                it.type == DocumentChange.Type.ADDED -> {
                    val newUser = it.document.toObject(User::class.java)
                    userDAO.insert(userMapper.authToUserTable(newUser))

                }
                it.type == DocumentChange.Type.MODIFIED -> {
                    val updatedUser = it.document.toObject(User::class.java)
                    userDAO.update(userMapper.authToUserTable(updatedUser))

                }
                it.type == DocumentChange.Type.REMOVED -> {
                    val deletedUser = it.document.toObject(User::class.java)
                    userDAO.delete(userMapper.authToUserTable(deletedUser))
                }
            }
        }
    }
}