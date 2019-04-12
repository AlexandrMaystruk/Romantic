package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User

interface HikesRepository {

    fun downloadHikeFromFireStore(): Single<List<Hike>>

    fun saveToLocalDB(hike: Hike): Completable

    fun saveToLocalDB(hikes: List<Hike>): Completable

    fun addParticipant(participant: Participant): Completable

    fun addBossToHike(hikeId: Long): Completable

    fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>>

    fun getCurrentUserFromPref(): User

    fun provideUserHikes(currentUser: User): Single<List<Hike>>

}