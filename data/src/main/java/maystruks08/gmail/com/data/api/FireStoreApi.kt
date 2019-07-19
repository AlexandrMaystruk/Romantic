package maystruks08.gmail.com.data.api

import android.util.Log
import com.google.firebase.firestore.*
import durdinapps.rxfirebase2.RxCompletableHandler
import durdinapps.rxfirebase2.RxFirestore
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.firebase.POJOHike
import maystruks08.gmail.com.domain.entity.firebase.POJOParticipant
import maystruks08.gmail.com.domain.entity.firebase.POJORoute
import javax.inject.Inject

class FireStoreApi @Inject constructor(private val fireStore: FirebaseFirestore) {

    fun saveUser(user: User): Completable {
        val referenceUser = fireStore.collection(COLLECTION_USER).document(user.id)
        return RxFirestore.setDocument(referenceUser, user)
    }

    fun getUsers(): Maybe<QuerySnapshot> {
        val referenceUser = fireStore.collection(COLLECTION_USER)
        return RxFirestore.getCollection(referenceUser)
    }

    fun getHikeGroup(hikeId: Long): Maybe<DocumentSnapshot> {
        val referenceGroup = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(referenceGroup)
    }


    fun getAllHikes(): Maybe<QuerySnapshot> {
        val referenceHike = fireStore.collection(COLLECTION_HIKE)
        return RxFirestore.getCollection(referenceHike)
    }

    fun uploadHike(hike: POJOHike): Completable {
        val referenceHike = fireStore.collection(COLLECTION_HIKE).document(HIKE + hike.id)
        return RxFirestore.setDocument(referenceHike, hike)
    }

    fun setParticipantToGroup(hikeId: Long, participant: POJOParticipant): Completable {
        val referenceGroup = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(referenceGroup).flatMapSingle { snapshot ->
            val participants = mutableMapOf<String, POJOParticipant>()
            snapshot.data?.values?.forEach { any ->
                (any as?  Map<*, *>)?.let {
                    val part = POJOParticipant.fromMap(it)
                    participants.put(part.id, POJOParticipant.fromMap(it))
                }
            }
            participants[participant.id] = participant
            return@flatMapSingle Single.just(participants)
        }.flatMapCompletable {
            setDocument(referenceGroup, it)
        }.onErrorResumeNext {
            setDocument(referenceGroup, mutableMapOf(participant.id to participant))
        }
    }

    fun setParticipantsToGroup(hikeId: Long, participants: List<POJOParticipant>): Completable {
        val referenceGroup = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(referenceGroup).flatMapSingle { snapshot ->
            val participantsMap = mutableMapOf<String, POJOParticipant>()
            snapshot.data?.values?.forEach { any ->
                (any as?  Map<*, *>)?.let {
                    val part = POJOParticipant.fromMap(it)
                    participantsMap.put(part.id, POJOParticipant.fromMap(it))
                }
            }
            participants.forEach {
                participantsMap[it.id] = it
            }
            return@flatMapSingle Single.just(participantsMap)
        }.flatMapCompletable {
            setDocument(referenceGroup, it)
        }.onErrorResumeNext {
            val map = mutableMapOf<String, Any>().apply {
                participants.forEach {
                    put(it.id, it)
                }
            }
            setDocument(referenceGroup, map)
        }
    }

    fun removeParticipantFromGroup(hikeId: Long, participant: POJOParticipant): Completable {
        val referenceGroup = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(referenceGroup).flatMapSingle { snapshot ->
            val participants = mutableMapOf<String, POJOParticipant>()
            snapshot.data?.values?.forEach { any ->
                (any as?  Map<*, *>)?.let {
                    val part = POJOParticipant.fromMap(it)
                    if (part.id != participant.id)
                        participants[part.id] = POJOParticipant.fromMap(it)
                }
            }
            return@flatMapSingle Single.just(participants)
        }.flatMapCompletable {
            setDocument(referenceGroup, it)
        }
    }

    fun removeParticipantFromGroup(hikeId: Long, participantId: String): Completable {
        val referenceGroup = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.getDocument(referenceGroup).flatMapSingle { snapshot ->
            val participants = mutableMapOf<String, POJOParticipant>()
            snapshot.data?.values?.forEach { any ->
                (any as?  Map<*, *>)?.let {
                    val part = POJOParticipant.fromMap(it)
                    if (part.id != participantId)
                        participants[part.id] = POJOParticipant.fromMap(it)
                }
            }
            return@flatMapSingle Single.just(participants)
        }.flatMapCompletable {
            setDocument(referenceGroup, it)
        }
    }

    fun deleteHike(hikeId: Long): Completable {
        val hikeReference = fireStore.collection(COLLECTION_HIKE).document(HIKE + hikeId)
        val groupReference = fireStore.collection(COLLECTION_HIKE_GROUP).document(HIKE + hikeId)
        return RxFirestore.deleteDocument(hikeReference).andThen(RxFirestore.deleteDocument(groupReference))
    }



    fun getHikeRoute(hikeId: Long): Maybe<DocumentSnapshot>{
        val routeReference = fireStore.collection(COLLECTION_HIKE_ROUTE).document(HIKE + hikeId)
        return RxFirestore.getDocument(routeReference)
    }

    fun uploadRoute(hikeId: Long, route: POJORoute): Completable{
        val routeReference = fireStore.collection(COLLECTION_HIKE_ROUTE).document(HIKE + hikeId)
        return RxFirestore.setDocument(routeReference, route)
    }

    fun updateHikeRoute(hikeId: Long, route: POJORoute): Completable{
        val routeReference = fireStore.collection(COLLECTION_HIKE_ROUTE).document(HIKE + hikeId)
        return RxFirestore.setDocument(routeReference, route)
    }


    //todo clean up this
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
        private const val COLLECTION_HIKE_ROUTE = "route"
        private const val COLLECTION_HIKE_TOOLS = "tools"
        private const val COLLECTION_HIKE_MATERIALS = "materials"




        private const val HIKE = "hike_"

        private const val TAG = "FireStoreApi"
    }
}