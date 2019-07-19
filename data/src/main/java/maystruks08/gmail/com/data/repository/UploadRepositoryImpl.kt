package maystruks08.gmail.com.data.repository


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import maystruks08.gmail.com.data.api.FireStoreApi
import maystruks08.gmail.com.data.mappers.HikeMapper
import maystruks08.gmail.com.data.mappers.ParticipantMapper
import maystruks08.gmail.com.data.mappers.RouteMapper
import maystruks08.gmail.com.data.room.dao.HikeDAO
import maystruks08.gmail.com.domain.repository.UploadRepository
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: FireStoreApi,
    private val hikeDAO: HikeDAO,
    private val hikeMapper: HikeMapper,
    private val participantMapper: ParticipantMapper,
    private val routeMapper: RouteMapper
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
        return hikeDAO.getNotUploadParticipants()
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
                                            hikeDAO.setAlreadyUpdated(participant.id)
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
                hikeDAO.getNotUploadParticipantsCount()
            }
    }

    override fun updateParticipants(): Single<Int> {
        return hikeDAO.getNotUpdateParticipants()
            .flatMapCompletable { tableItems ->
                val participantGroups = tableItems.groupBy { it.hikeId }
                return@flatMapCompletable Observable.fromIterable(participantGroups.keys)
                    .concatMapCompletable { key ->
                        participantGroups[key]?.let {
                            val fireStoreParticipants = participantMapper.toParticipantPOJOList(it)
                            api.setParticipantsToGroup(key, fireStoreParticipants)
                                .andThen(Completable.fromAction {
                                    fireStoreParticipants.forEach { participant ->
                                        hikeDAO.setAlreadyUpdated(participant.id)
                                    }
                                })
                        }
                    }

            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                hikeDAO.getNotUpdatedParticipantCount()
            }
    }

    override fun uploadNotUploadedRoutes(): Single<Int> {
        return hikeDAO.getNotUploadedRoutes()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreRoute = routeMapper.toRoutePOJO(tableItem)

                //todo remove hardcode hike id
                return@flatMapCompletable api.uploadRoute(0L, fireStoreRoute)
                    .andThen(Completable.fromAction { hikeDAO.setRouteUploaded(0L, fireStoreRoute.id) })
            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                hikeDAO.getNotUploadRoutesCount()
            }
    }

    override fun updateRoutes(): Single<Int> {
        return hikeDAO.getNotUpdatedRoutes()
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .flatMapCompletable { tableItem ->
                val fireStoreRoute = routeMapper.toRoutePOJO(tableItem)
                //todo remove hardcode hike id
                return@flatMapCompletable api.updateHikeRoute(0L, fireStoreRoute)
                    .andThen(Completable.fromAction { hikeDAO.setRouteAlreadyUpdated(fireStoreRoute.id) })
            }
            .andThen(Single.just(0))
            .onErrorResumeNext {
                hikeDAO.getNotUpdatedRoutesCount()
            }
    }

    companion object {
        private const val TAG = "UploadDataRepository"
    }
}