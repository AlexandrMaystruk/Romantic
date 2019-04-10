package maystruks08.gmail.com.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject

class ParticipantRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val userDAO: UserDAO,
    private val userMapper: UserMapper
) : ParticipantRepository {


    override fun getHikeParticipant(hikeId: String): Single<List<User>> {
        //todo change to filter by hike id
        return userDAO.getUsers().map { list ->
            list.map { userMapper.userTableToUser(it) }
        }
    }


    fun setUserToHikeGroup(user: User, hikeId: String, post: String): Completable{
        return api.setUserToHikeGroup(user, hikeId, post)
    }

    fun getAllUserFromFireStoreByHikeId(hikeId: String): Single<List<Participant>>{
        return api.getHikeGroupFromFireStore(hikeId).flatMapSingle { snapshot ->
            val result = mutableListOf<Participant>()
            snapshot.documents.map {
                val user = it.toObject(User::class.java)
                if(user != null){
                    val participant = Participant(user, UserPost.valueOf(it.id))
                    result.add(participant)
                    Log.d("Participant", "User post  ${it.id}")
                }
            }
            Single.just(result)
        }
    }

    fun getAllUserFromFireStore(): Single<List<User>>{
        return api.getUsersFromFireStore().flatMapSingle { snapshot ->
            val result = mutableListOf<User>()
            snapshot.documents.map {
                val user = it.toObject(User::class.java)
                if(user != null){
                    result.add(user)
                }
            }
            Single.just(result)
        }
    }
}