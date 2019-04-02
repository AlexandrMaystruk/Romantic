package maystruks08.gmail.com.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User

interface HikesRepository {

    fun downloadHikeData(): Single<List<Hike>>

    fun saveHikesToDb(hikes: List<Hike>): Completable

    fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>>

    fun getCurrentUser(): Single<User>

    fun provideUserHikes(currentUser: User): Single<List<Hike>>

}