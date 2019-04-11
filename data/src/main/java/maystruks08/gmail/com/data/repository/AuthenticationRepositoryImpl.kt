package maystruks08.gmail.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseUser
import io.reactivex.Single
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.domain.entity.User
import com.google.firebase.auth.UserProfileChangeRequest
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.room.dao.UserDAO


class AuthenticationRepositoryImpl @Inject constructor(
    private val fireBaseAuth: FirebaseAuth,
    private val userMapper: UserMapper,
    private val api: FireStoreApi,
    private val userDAO: UserDAO,
    private val pref: AuthPreferences

) :
    AuthenticationRepository {


    override fun getCurrentUser(): Completable {
        return if (fireBaseAuth.currentUser != null) {
            Completable.complete()
        } else
            Completable.error(Throwable())
    }

    override fun createFireBaseUser(email: String, password: String, displayName: String): Single<User> {
        return RxFirebaseAuth.createUserWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapSingle {
                val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(displayName).build()
                return@flatMapSingle RxFirebaseUser.updateProfile(it.user, profileUpdates)
                    .andThen(
                        Single.just(userMapper.fireBaseUserToUser(it.user))
                    )
            }
    }

    override fun addUserToFireStoreDb(user: User): Completable {
        return api.saveUserToFireStore(user)
    }

    override fun addUserToDb(user: User): Completable {
        return Completable.fromAction { userDAO.insert(userMapper.authToUserTable(user)) }.andThen(
            Completable.fromAction {
                pref.saveCurrentUser(user)
            })
    }

    override fun login(email: String, password: String): Completable {
        return RxFirebaseAuth.signInWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapCompletable {
                return@flatMapCompletable if (it.user != null) {
                    Completable.fromAction {
                        pref.saveCurrentUser(userMapper.fireBaseUserToUser(it.user))
                    }
                } else {
                    Completable.error(Throwable())
                }
            }
    }
}