package maystruks08.gmail.com.data.api

import android.util.Log
import com.google.firebase.firestore.*
import durdinapps.rxfirebase2.RxCompletableHandler
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.firebase.FireStoreHike
import maystruks08.gmail.com.domain.entity.firebase.FireStoreParticipant
import javax.inject.Inject

class FireStoreApi @Inject constructor(private val fireStore: FirebaseFirestore) {

    fun saveUser(user: User): Completable {
        val reference = fireStore.collection(COLLECTION_USER).document(user.id)
        return RxFirestore.setDocument(reference, user)
    }

    fun getUsers(): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_USER)
        return RxFirestore.getCollection(reference)
    }

    fun getHikeGroup(hikeId: Long): Maybe<DocumentSnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(reference)
    }


    fun getAllHikes(): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE)
        return RxFirestore.getCollection(reference)
    }

    fun uploadHike(hike: FireStoreHike): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hike.id)
        return RxFirestore.setDocument(reference, hike)
    }

    fun setParticipantToGroup(hikeId: Long, participant: FireStoreParticipant): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return setDocument(reference, mapOf(participant.id to participant))
    }

    fun removeParticipantFromGroup(hikeId: Long, participant: FireStoreParticipant): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hikeId).collection(COLLECTION_HIKE_GROUP)
            .document(participant.post.name)
        return RxFirestore.deleteDocument(reference)
    }

    private fun setDocument(ref: DocumentReference, map: Map<String, Any>): Completable {
        return Completable.create { emitter -> RxCompletableHandler.assignOnTask(emitter, ref.set(map)) }
    }

    fun setUpdateListener(hikes: (List<DocumentChange>) -> Unit, users: (List<DocumentChange>) -> Unit) {
        fireStore.collection(COLLECTION_HIKE).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "listen:error", e)
                return@addSnapshotListener
            }

            hikes(snapshot?.documentChanges!!)
        }


        fireStore.collection(COLLECTION_USER).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "listen:error", e)
                return@addSnapshotListener
            }

            users(snapshot?.documentChanges!!)

        }
    }


    companion object {
        private const val COLLECTION_USER = "user"
        private const val COLLECTION_HIKE = "hike"
        private const val COLLECTION_HIKE_GROUP = "group"

        private const val DOCUMENT_TOOLS = "tools"
        private const val HIKE = "hike_"

        private const val TAG = "FireStoreApi"
    }
}