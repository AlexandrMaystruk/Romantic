//package maystruks08.gmail.com.romantic.core
//
//import android.provider.SyncStateContract
//import android.util.Log
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.iid.FirebaseInstanceId
//
//class MyFirebaseInstanceIDService : FirebaseInstanceId() {
//    private val TAG = "MyFirebaseIIDService"
//
//    override fun onTokenRefresh() {
//        // Get updated InstanceID token.
//        val refreshedToken = FirebaseInstanceId.getInstance().token
//        Log.d(TAG, "Refreshed token: $refreshedToken")
//
//        sendRegistrationToServer(refreshedToken)
//    }
//
//
//    private fun sendRegistrationToServer(token: String?) {
//        SharedPrefUtil(applicationContext).saveString(SyncStateContract.Constants.ARG_FIREBASE_TOKEN, token)
//
//        if (FirebaseAuth.getInstance().currentUser != null) {
//            FirebaseDatabase.getInstance()
//                .getReference()
//                .child(Constants.ARG_USERS)
//                .child(FirebaseAuth.getInstance().currentUser!!.uid)
//                .child(Constants.ARG_FIREBASE_TOKEN)
//                .setValue(token)
//        }
//    }
