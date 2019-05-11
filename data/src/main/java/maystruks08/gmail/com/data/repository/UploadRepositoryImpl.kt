package maystruks08.gmail.com.data.repository


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.data.room.dao.ParticipantDAO
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.domain.repository.UploadRepository
import java.util.*
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantDAO: ParticipantDAO,
    private val participantMapper: ParticipantMapper
) : UploadRepository {

    override fun uploadNotUploadedHikes(): Single<Int> {
        return hikeDAO.getNotUploadHikes()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreHike = hikeMapper.toFireBaseHike(tableItem)
                return@flatMapCompletable api.uploadHike(fireStoreHike)
                    .andThen(Completable.fromAction { hikeDAO.setHikesUploaded(fireStoreHike.id) })
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
            .flatMapCompletable { tableItems ->
                val participantGroups = tableItems.groupBy { it.hikeId }
                return@flatMapCompletable Observable.fromIterable(participantGroups.keys)
                    .concatMapCompletable { key ->
                        participantGroups[key]?.let {
                            val fireStoreParticipants = participantMapper.toParticipantPOJOList(it)
                            api.setParticipantsToGroup(key, fireStoreParticipants)
                                .andThen(
                                    Completable.fromAction {
                                        fireStoreParticipants.forEach { participant ->
                                            participantDAO.setAlreadyUpdated(participant.id)
                                        }
                                    }.subscribeOn(
                                        Schedulers.io()
                                    )
                                )
                        }
                    }

            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                participantDAO.getNotUploadParticipantsCount()
            }
    }

    override fun updateParticipants(): Single<Int> {
        return participantDAO.getNotUpdateParticipants()
            .flatMapCompletable { tableItems ->
                val participantGroups = tableItems.groupBy { it.hikeId }
                return@flatMapCompletable Observable.fromIterable(participantGroups.keys)
                    .concatMapCompletable { key ->
                        participantGroups[key]?.let {
                            val fireStoreParticipants = participantMapper.toParticipantPOJOList(it)
                            api.setParticipantsToGroup(key, fireStoreParticipants)
                                .andThen(Completable.fromAction {
                                    fireStoreParticipants.forEach { participant ->
                                        participantDAO.setAlreadyUpdated(participant.id)
                                    }
                                })
                        }
                    }

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