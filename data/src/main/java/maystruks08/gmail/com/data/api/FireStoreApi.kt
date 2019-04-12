package maystruks08.gmail.com.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.entity.firebase.FireBaseHike
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

    fun getHikeGroup(hikeId: Long): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hikeId).collection(COLLECTION_HIKE_GROUP)
        return RxFirestore.getCollection(reference)
    }


    fun getAllHikes(): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE)
        return RxFirestore.getCollection(reference)
    }

    fun uploadHike(hike: FireBaseHike): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hike.id)
        return RxFirestore.setDocument(reference, hike)
    }

    fun setParticipantToGroup(participant: FireStoreParticipant): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + participant.hikeId).collection(COLLECTION_HIKE_GROUP).document(participant.post.name)
        return RxFirestore.setDocument(reference, participant)
    }



    companion object {
        private const val COLLECTION_USER = "user"
        private const val HIKE = "hike_"
        private const val COLLECTION_HIKE = "hike"
        private const val COLLECTION_HIKE_GROUP = "hike_group"
        private const val DOCUMENT_TOOLS = "tools"

    }

}