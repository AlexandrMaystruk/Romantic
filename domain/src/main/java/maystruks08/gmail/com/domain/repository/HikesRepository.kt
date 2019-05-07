package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User

interface HikesRepository {

    fun createNewHike(hike: Hike): Completable

    fun downloadHikeFromFireStore(): Single<List<Hike>>

    fun cashHike(hike: Hike): Completable

    fun cashHikes(hikes: List<Hike>): Completable

    fun addParticipant(hikeId: Long, participant: Participant): Completable

    fun provideHikes(typeHike: TypeHike): Single<Pair<List<Hike>, Int>>

    fun getCurrentUserFromPref(): User

    fun provideUserHikes(): Single<List<Hike>>

    fun downloadParticipantsFromFireStore(hikeId: Long): Single<List<Participant>>

}