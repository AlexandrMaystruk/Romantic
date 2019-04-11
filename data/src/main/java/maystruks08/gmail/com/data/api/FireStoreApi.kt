package maystruks08.gmail.com.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import javax.inject.Inject

class FireStoreApi @Inject constructor(private val fireStore: FirebaseFirestore) {

    fun saveUserToFireStore(user: User): Completable {
        val reference = fireStore.collection(COLLECTION_USER).document(user.id)
        return RxFirestore.setDocument(reference, user)
    }


    fun getUsersFromFireStore(): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_USER)
        return RxFirestore.getCollection(reference)
    }

    fun getHikeGroupFromFireStore(hikeId: Long): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hikeId).collection(COLLECTION_HIKE_GROUP)
        return RxFirestore.getCollection(reference)
    }


    fun getAllHikesFromFireStore(): Maybe<QuerySnapshot> {
        val reference = fireStore.collection(COLLECTION_HIKE)
        return RxFirestore.getCollection(reference)
    }

    fun saveHikeToFirestore(hike: Hike): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hike.id.toString())
        return RxFirestore.setDocument(reference, hike)
    }


    fun setUserToHikeGroup(user: User, hikeId: Long, post: String): Completable {
        val reference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hikeId).collection(COLLECTION_HIKE_GROUP)
            .document(post)
        val part = Participant(
            UserPost.valueOf(post),
            hikeId,
            user.id,
            user.displayName,
            user.email,
            user.userExperienceMountain,
            user.userExperienceWalking,
            user.userExperienceWater,
            user.userExperienceSki,
            user.userPhotoUrl
        )
        return RxFirestore.setDocument(reference, part)
    }


    companion object {
        private const val COLLECTION_USER = "user"
        private const val HIKE = "hike_"
        private const val COLLECTION_HIKE = "hike"
        private const val COLLECTION_HIKE_GROUP = "hike_group"
        private const val DOCUMENT_TOOLS = "tools"

    }

}