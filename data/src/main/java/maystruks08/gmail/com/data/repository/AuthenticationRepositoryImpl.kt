package maystruks08.gmail.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseUser
import maystruks08.gmail.com.data.mappers.UserMapper


class AuthenticationRepositoryImpl @Inject constructor(private val fireBaseAuth: FirebaseAuth,
                                                       private val mapper: UserMapper) :
    AuthenticationRepository {

    override fun signUp(email: String, password: String, displayName: String): Single<User> {
        return RxFirebaseAuth.createUserWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapSingle {
                //add mapper
                Single.just(User("test"))
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