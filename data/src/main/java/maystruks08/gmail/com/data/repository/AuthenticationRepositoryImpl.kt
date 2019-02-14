package maystruks08.gmail.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject
import durdinapps.rxfirebase2.RxFirebaseAuth

class AuthenticationRepositoryImpl @Inject constructor(
    private val fireBaseAuth: FirebaseAuth
) :
    AuthenticationRepository {

    override fun getCurrentUser(): Completable {
        return if (fireBaseAuth.currentUser != null) {
            Completable.complete()
        } else
            Completable.error(Throwable())
    }

    override fun signUp(email: String, password: String, displayName: String): Completable {
        return RxFirebaseAuth.createUserWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapCompletable {
                return@flatMapCompletable if (it.user != null) {
                    Completable.complete()
                } else {
                    Completable.error(Throwable())
                }
            }
    }

    override fun login(email: String, password: String): Completable {
        return RxFirebaseAuth.signInWithEmailAndPassword(fireBaseAuth, email, password)
            .flatMapCompletable {
                return@flatMapCompletable if (it.user != null) {
                    Completable.complete()
                } else {
                    Completable.error(Throwable())
                }
            }
    }
}