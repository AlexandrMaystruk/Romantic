package maystruks08.gmail.com.data.repository

import android.app.Activity
import android.content.ContentValues.TAG
import android.provider.SyncStateContract
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {

    override fun singup(email: String, password: String, displayName: String): Single<User> {

        override fun performFirebaseRegistration(
            activity: Activity,
            email: String,
            password: String,
            displayName: String
        ) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, object : OnCompleteListener<AuthResult>() {
                    fun onComplete(task: Task<AuthResult>) {
                        Log.e(TAG, "performFirebaseRegistration:onComplete:" + task.isSuccessful())

                        if (!task.isSuccessful()) {
                            mOnRegistrationListener.onFailure(task.getException().getMessage())
                        } else {


                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(displayName)
                                .build()

                            if (task.getResult().getUser() != null) {
                                task.getResult().getUser().updateProfile(profileUpdates)
                            }

                            mOnRegistrationListener.onSuccess(task.getResult().getUser())

                            //для бд РУМ
                            //                            Constants.USER_ID_FIREBASE =  FirebaseAuth.getInstance().getUid();
                        }
                    }
                })
        }
    }

    override fun login(email: String, password: String): Completable {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, object : OnCompleteListener<AuthResult>() {
                fun onComplete(task: Task<AuthResult>) {
                    Log.d(TAG, "performFirebaseLogin:onComplete:" + task.isSuccessful())
                    if (task.isSuccessful()) {

                        //для бд РУМ
                        //                            Constants.USER_ID_FIREBASE =  FirebaseAuth.getInstance().getUid();

                        mOnLoginListener.onSuccess(task.getResult().toString())
                        updateFirebaseToken(
                            task.getResult().getUser().getUid(),
                            SharedPrefUtil(activity.applicationContext).getString(SyncStateContract.Constants.ARG_FIREBASE_TOKEN, null)
                        )
                    } else {
                        mOnLoginListener.onFailure(task.getException().getMessage())
                    }
                }
            })
    }

    override fun logout(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun performFirebaseLogin(activity: Activity, email: String, password: String) {

    }

    private fun updateFirebaseToken(uid: String, token: String) {
        FirebaseDatabase.getInstance()
            .getReference()
            .child(Constants.ARG_USERS)
            .child(uid)
            .child(Constants.ARG_FIREBASE_TOKEN)
            .setValue(token)
    }
}