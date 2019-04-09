package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.room.dao.UserDAO
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val userDAO: UserDAO,
    private val userMapper: UserMapper
) : UserRepository {

    override fun addNewUserToFireStore(user: User): Completable {
        return api.saveUserToFireStore(user)
    }

    override fun getHikeParticipant(hikeId: String): Single<List<User>> {
        //todo change to filter by hike id
        return userDAO.getUsers().map { list ->
            list.map { userMapper.userTableToUser(it) }
        }
    }

    override fun addNewUser(user: User): Completable {
        return userDAO.insert(userMapper.authToUserTable(user))
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