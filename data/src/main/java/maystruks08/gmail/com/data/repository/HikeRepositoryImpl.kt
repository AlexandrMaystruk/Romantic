package maystruks08.gmail.com.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.repository.HikesRepository
import javax.inject.Inject

class HikeRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val pref: AuthPreferences
) : HikesRepository {

    override fun createNewHike(hike: Hike): Completable {
        return Completable.fromAction {
            val hikeTable = hikeMapper.toHikeTable(hike)
            hikeDAO.insert(hikeTable)
        }
            .andThen(hikeDAO.addUserToHikeGroup(createParticipant(hike.id)))
            .andThen(api.saveHikeToFirestore(hike))
            .andThen(api.setUserToHikeGroup(getCurrentUser(), hike.id, UserPost.BOSS.name))
    }

    private fun createParticipant(hikeId: Long): ParticipantTable {
        val currentUser = getCurrentUser()
        return ParticipantTable(
            userId = currentUser.id,
            post = UserPost.BOSS.name,
            hikeId = hikeId,
            displayName = currentUser.displayName,
            email = currentUser.email,
            userExperienceMountain = currentUser.userExperienceMountain,
            userExperienceWalking = currentUser.userExperienceWalking,
            userExperienceSki = currentUser.userExperienceSki,
            userExperienceWater = currentUser.userExperienceWater,
            userPhotoUrl = currentUser.userPhotoUrl
        )
    }

    override fun downloadHikeFromFireStore(): Single<List<Hike>> {
        return api.getAllHikesFromFireStore().flatMapSingle { snapshot ->
            val result = mutableListOf<Hike>()
            snapshot.documents.map {
                val hike = it.toObject(Hike::class.java)
                if (hike != null) {
                    result.add(hike)
                }
            }
            Single.just(result)
        }

    }

    override fun saveHikeToDb(hike: Hike): Completable {
        return Completable.fromAction { hikeDAO.insert(hikeMapper.toHikeTable(hike)) }
    }

    override fun saveHikesToDb(hikes: List<Hike>): Completable {
        return Completable.fromAction { hikeDAO.insert(hikeMapper.toHikeTableList(hikes)) }
    }

    override fun provideHikes(typeHike: TypeHike?): Single<Pair<List<Hike>, Int>> {
        return if (typeHike == null) {//todo fix query
            hikeDAO.getHikesByUserId(getCurrentUser().id).map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, 0)
            }
        } else {//todo fix query
            hikeDAO.getHikesByType(getCurrentUser().id, typeHike.type.toString()).map { list ->
                val result = list.map { hikeMapper.toHikeFromTable(it) }
                Pair(result, typeHike.type)
            }
        }
    }

    override fun getCurrentUser(): User {
        return pref.getCurrentUser()!!
    }
//todo fix query
    override fun provideUserHikes(currentUser: User): Single<List<Hike>> {
        return hikeDAO.getHikesByUserId(currentUser.id).map { list ->
            list.map { hikeMapper.toHikeFromTable(it) }

        }
    }
}