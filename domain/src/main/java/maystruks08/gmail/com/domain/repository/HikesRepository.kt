package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.*

interface HikesRepository {

    fun createNewHike(hike: Hike): Completable

    fun downloadHikeFromFireStore(): Single<List<Hike>>

    fun cashHike(hike: Hike): Completable

    fun cashHikes(hikes: List<Hike>): Completable

    fun cashParticipant(hikeId: Long, participant: Participant): Completable

    fun provideHikes(typeHike: TypeHike): Single<Pair<List<Hike>, Int>>

    fun provideUserHikes(): Single<List<Hike>>

    fun downloadParticipantsFromFireStore(hikeId: Long): Single<List<Participant>>

    fun getCurrentUserFromPref(): User

    fun getUsers(): Single<List<User>>

    fun getHikeParticipants(hikeId: Long): Single<List<Participant>>

    fun setUserToHikeGroup(hikeId: Long,user: User, post: UserPost): Completable

    fun setParticipantHikeGroup(hikeId: Long,participant: Participant): Completable

    fun setParticipantsToHikeGroup(hikeId: Long, participants: List<Participant>): Completable

    fun removeParticipantFromHikeGroup(hikeId: Long, participant: Participant): Completable

    fun leaveFromHikeGroup(hikeId: Long): Completable

    fun removeHike(hikeId: Long): Completable

    fun updateCashedUsers(cashUsers: List<User>) : Single<List<User>>


}