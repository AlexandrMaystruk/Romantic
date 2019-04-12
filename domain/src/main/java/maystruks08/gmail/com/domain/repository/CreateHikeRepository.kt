package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User

interface CreateHikeRepository {

    fun createNewHike(hike: Hike): Completable

    fun downloadHikeFromFireStore(): Single<List<Hike>>

    fun saveHikeToDb(hike: Hike): Completable

    fun addParticipant(hike: Participant): Completable

    fun saveHikesToDb(hikes: List<Hike>): Completable

    fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>>

    fun getCurrentUser(): User

    fun provideUserHikes(currentUser: User): Single<List<Hike>>

}