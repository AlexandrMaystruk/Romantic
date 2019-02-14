package maystruks08.gmail.com.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import javax.inject.Inject
import maystruks08.gmail.com.domain.repository.LogOutRepository

class LogOutRepositoryImpl @Inject constructor(
    private val fireBaseAuth: FirebaseAuth
) :
    LogOutRepository {

    override fun logout(): Completable {
        return Completable.fromAction {
            fireBaseAuth.signOut()
        }
    }
}