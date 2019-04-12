package maystruks08.gmail.com.data.repository


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.domain.repository.UploadRepository
import java.util.*
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantDAO: ParticipantDAO,
    private val participantMapper: ParticipantMapper) : UploadRepository {

    override fun uploadNotUploadedHikes(): Single<Int> {
        return hikeDAO.getNotUploadHikes()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreHike = hikeMapper.toFireBaseHike(tableItem)
                return@flatMapCompletable api.uploadHike(fireStoreHike)
                    .andThen(Completable.fromAction { hikeDAO.setHikesUploaded(fireStoreHike.id, Date().time) })
            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                hikeDAO.getNotUploadHikesCount()
            }
    }

    override fun updateHike(): Single<Int> {
        return hikeDAO.getNotUpdateHikes()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreHike = hikeMapper.toFireBaseHike(tableItem)
                return@flatMapCompletable api.uploadHike(fireStoreHike)
                    .andThen(Completable.fromAction { hikeDAO.setAlreadyUpdated(fireStoreHike.id) })

            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                hikeDAO.getNotUpdatedHikesCount()
            }
    }

    override fun uploadNotUploadedParticipants(): Single<Int> {
        return participantDAO.getNotUploadParticipants()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreParticipant = participantMapper.toFireStoreParticipant(tableItem)
                return@flatMapCompletable api.setParticipantToGroup(fireStoreParticipant)
                    .andThen(
                        Completable.fromAction {
                            participantDAO.setParticipantUploaded(
                                fireStoreParticipant.id,
                                Date().time
                            )
                        })
            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                participantDAO.getNotUploadParticipantsCount()
            }
    }

    override fun updateParticipants(): Single<Int> {
        return participantDAO.getNotUpdateParticipants()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreParticipants = participantMapper.toFireStoreParticipant(tableItem)
                return@flatMapCompletable api.setParticipantToGroup(fireStoreParticipants)
                    .andThen(Completable.fromAction { participantDAO.setAlreadyUpdated(fireStoreParticipants.id) })

            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                participantDAO.getNotUpdatedParticipantCount()

            }
    }

    companion object {
        private const val TAG = "UploadDataRepository"
    }
}