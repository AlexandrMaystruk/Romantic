package maystruks08.gmail.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseUser
import maystruks08.gmail.com.data.mappers.UserMapper
import maystruks08.gmail.com.data.room.dao.UserDAO


class AuthenticationRepositoryImpl @Inject constructor(private val fireBaseAuth: FirebaseAuth,
                                                       private val userDao: UserDAO,
                                                       private val mapper: UserMapper) :
    AuthenticationRepository {

    override fun signUp(email: String, password: String, displayName: String): Completable{
        return RxFirebaseAuth.createUserWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapCompletable {
                Completable.fromAction {
                    userDao.insert(mapper.authInfoToUserTable(it))
                }
            }
    }

    override fun login(email: String, password: String): Completable {
        return RxFirebaseAuth.signInWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapCompletable {
                RxFirebaseUser.getToken(it.user, true)
                    .flatMapCompletable {
                        Completable.fromAction {

                            //save token
                        }
                    }
            }
    }

    override fun logout(): Completable {
        return Completable.fromAction {
            FirebaseAuth.getInstance().signOut()
        }
    }
}