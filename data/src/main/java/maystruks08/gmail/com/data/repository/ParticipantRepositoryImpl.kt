package maystruks08.gmail.com.data.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.repository.ParticipantRepository
import javax.inject.Inject

class ParticipantRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val participantDAO: ParticipantDAO,
    private val participantMapper: ParticipantMapper
) : ParticipantRepository {


    override fun getHikeParticipant(hikeId: String): Single<List<Participant>> {
        return participantDAO.getParticipantsByHikeId(hikeId).map { list ->
            list.map { participantMapper.toParticipant(it) }
        }
    }

    override fun setUserToHikeGroup(user: User, hikeId: Long, post: UserPost): Completable {
        return api.setParticipantToGroup(participantMapper.toFireStoreParticipant(user, hikeId, post))
    }

    override fun getAllParticipantsFromFireStoreByHikeId(hikeId: Long): Single<List<Participant>> {
        return api.getHikeGroup(hikeId).flatMapSingle { snapshot ->
            val result = mutableListOf<Participant>()
            snapshot.documents.map {
                val participant = it.toObject(Participant::class.java)
                if (participant != null) {
                    result.add(participant)
                    Log.d("Participant", "User post  ${it.id}")
                }
            }
            Single.just(result)
        }
    }

    override fun getAllUserFromFireStore(): Single<List<User>> {
        return api.getUsers().flatMapSingle { snapshot ->
            val result = mutableListOf<User>()
            snapshot.documents.map {
                val user = it.toObject(User::class.java)
                if (user != null) {
                    result.add(user)
                }
            }
            Single.just(result)
        }
    }
}