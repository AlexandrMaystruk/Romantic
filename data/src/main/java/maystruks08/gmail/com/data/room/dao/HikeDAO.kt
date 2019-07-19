package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.*

@Dao
abstract class HikeDAO {


    /** WORK WITH HIKE ENTITY */
    @Transaction
    open fun createHike(hike: HikeTable, participant: ParticipantTable) {
        insert(hike)
        insertParticipant(participant)
        insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hike.id, participantId = participant.id))
    }

    @Transaction
    open fun cashHike(hike: HikeTable, participant: List<ParticipantTable>) {
        insert(hike)
        participant.forEach {
            insertParticipant(it)
            insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hike.id, participantId = it.id))
        }
    }


    @Query("SELECT * FROM hikes")
    abstract fun getHikes(): Single<List<HikeTable>>

    /** Get hikes by participant id **/
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM hikes INNER JOIN hike_participant_join ON hikes.id = hike_participant_join.hikeId WHERE hike_participant_join.participantId = :participantId")
    abstract fun getHikesByUserId(participantId: String): Single<List<HikeTable>>

    /** Get hikes by participant id and type Hike**/
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM hikes INNER JOIN hike_participant_join ON hikes.id = hike_participant_join.hikeId WHERE hike_participant_join.participantId = :participantId AND hikes.type = :type ")
    abstract fun getHikes(participantId: String, type: Int): Single<List<HikeTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg hikes: HikeTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(hikes: List<HikeTable>)

    /** Work with hike updates **/
    //need to upload if upload is null
    @Query("SELECT * FROM hikes WHERE upload IS NULL")
    abstract fun getNotUploadHikes(): Single<List<HikeTable>>

    @Query("SELECT COUNT(*) FROM hikes WHERE upload IS NULL")
    abstract fun getNotUploadHikesCount(): Single<Int>

    @Query("UPDATE hikes SET upload = 1 WHERE id= :hikeId")
    abstract fun setHikesUploaded(hikeId: Long)


    // already updated = 1 , need to update = 0
    @Query("SELECT * FROM hikes WHERE needToUpdate IS 1")
    abstract fun getNotUpdateHikes(): Single<List<HikeTable>>

    @Query("SELECT COUNT(*) FROM hikes WHERE needToUpdate IS 1")
    abstract fun getNotUpdatedHikesCount(): Single<Int>

    @Query("UPDATE hikes SET needToUpdate = 1  WHERE id IS :hikeId")
    abstract fun setNeedToUpdate(hikeId: Long)

    @Query("UPDATE hikes SET needToUpdate = 0  WHERE id IS :hikeId")
    abstract fun setAlreadyUpdated(hikeId: Long)


    /** WORK WITH PARTICIPANT ENTITY */
    @Transaction
    open fun addUserToHikeGroup(hikeId: Long, participant: ParticipantTable) {
        insertParticipant(participant)
        insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hikeId, participantId = participant.id))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertParticipant(vararg participant: ParticipantTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHikeParticipantJoin(hikeParticipantJoin: HikeParticipantJoin)


    @Transaction
    open fun addUsersToHikeGroup(hikeId: Long, participant: List<ParticipantTable>) {
        participant.forEach {
            addParticipant(it)
            insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hikeId, participantId = it.id))
        }
    }

    @Transaction
    open fun deleteUserFromHikeGroup(participantId: String) {
        deleteParticipant(participantId)
        deleteHikeParticipantJoinByParticipantId(participantId)
    }

    @Transaction
    open fun deleteUserFromHikeGroup(participant: ParticipantTable) {
        delete(participant)
        deleteHikeParticipantJoinByParticipantId(participant.id)
    }

    @Transaction
    open fun deleteUsersFromHikeGroup(participant: List<ParticipantTable>) {
        participant.forEach {
            delete(it)
            deleteHikeParticipantJoinByParticipantId(it.id)
        }
    }

    // get participant by hike id
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM participants INNER JOIN hike_participant_join ON hike_participant_join.participantId = participants.id WHERE hike_participant_join.hikeId = :hikeId")
    abstract fun getParticipantsByHikeId(hikeId: Long): Single<List<ParticipantTable>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT COUNT(*) FROM participants INNER JOIN hike_participant_join ON hike_participant_join.participantId = participants.id WHERE hike_participant_join.hikeId = :hikeId")
    abstract fun getGroupCountByHikeId(hikeId: Long): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addParticipant(vararg participant: ParticipantTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addParticipants(participants: List<ParticipantTable>)

    @Query("DELETE FROM hike_participant_join  WHERE hikeId LIKE :hikeId")
    abstract fun removeGroup(hikeId: Long): Int


    @Query("DELETE FROM hike_participant_join  WHERE participantId = :participantId")
    abstract fun deleteHikeParticipantJoinByParticipantId(participantId: String): Int

    @Query("DELETE FROM participants  WHERE id = :participantId")
    abstract fun deleteParticipant(participantId: String): Int

    @Delete
    abstract fun delete(participant: ParticipantTable)

    @Delete
    abstract fun delete(participants: List<ParticipantTable>): Single<Int>

    @Update
    abstract fun update(participant: ParticipantTable)

    @Update
    abstract fun update(participants: List<ParticipantTable>)


    ////////work with updates
    @Query("SELECT * FROM participants WHERE upload IS NULL")
    abstract fun getNotUploadParticipants(): Single<List<ParticipantTable>>

    @Query("SELECT COUNT(*) FROM participants WHERE upload IS NULL")
    abstract fun getNotUploadParticipantsCount(): Single<Int>

    @Query("UPDATE participants SET upload = :date  WHERE id = :userId ")
    abstract fun setParticipantUploaded(userId: String, date: Long)


    // already updated = 1 , need to update = 0
    @Query("SELECT * FROM participants WHERE updated IS 0")
    abstract fun getNotUpdateParticipants(): Single<List<ParticipantTable>>

    @Query("SELECT COUNT(*) FROM participants WHERE updated IS 0")
    abstract fun getNotUpdatedParticipantCount(): Single<Int>

    @Query("UPDATE participants SET updated = 0  WHERE id IS :userId")
    abstract fun setNeedToUpdate(userId: String)

    @Query("UPDATE participants SET updated = 1  WHERE id IS :userId")
    abstract fun setAlreadyUpdated(userId: String)


    /** WORK WITH ROUTE ENTITY */
    @Transaction
    open fun addRoteToHike(hikeId: Long, route: RouteTable) {
        insert(route)
        insertHikeRouteJoin(HikeRouteJoin(hikeIdJoin = hikeId, routeIdJoin = route.id))
    }

    // get route by id
    @Query("SELECT * FROM routes WHERE id = :routeId")
    abstract fun getRouteById(routeId: Long): Single<RouteTable>

    // get routes by hike id
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM routes INNER JOIN hike_route_join ON routes.id = hike_route_join.routeIdJoin WHERE hike_route_join.hikeIdJoin = :hikeId")
    abstract fun getRoutesByHikeId(hikeId: Long): Single<List<RouteTable>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg route: RouteTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHikeRouteJoin(hikeRouteTable: HikeRouteJoin)

    /** Work with route updates **/
    @Query("SELECT * FROM routes WHERE upload IS NULL")
    abstract fun getNotUploadedRoutes(): Single<List<RouteTable>>

    @Query("SELECT COUNT(*) FROM routes WHERE upload IS NULL")
    abstract fun getNotUploadRoutesCount(): Single<Int>

    @Query("UPDATE routes SET upload = :date  WHERE id = :routeId ")
    abstract fun setRouteUploaded(routeId: Long, date: Long)


    // already updated = 1 , need to update = 0
    @Query("SELECT * FROM routes WHERE updated IS 0")
    abstract fun getNotUpdatedRoutes(): Single<List<RouteTable>>

    @Query("SELECT COUNT(*) FROM routes WHERE updated IS 0")
    abstract fun getNotUpdatedRoutesCount(): Single<Int>

    @Query("UPDATE routes SET updated = 0  WHERE id IS :routeId")
    abstract fun setNeedToUpdateRoute(routeId: Long)

    @Query("UPDATE routes SET updated = 1  WHERE id IS :routeId")
    abstract fun setRouteAlreadyUpdated(routeId: Long)


    /** UPDATE HIKE */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(vararg hikes: HikeTable)

    @Query("SELECT * FROM hikes where id = :idHike ")
    abstract fun getById(idHike: Long): HikeTable

    @Update
    abstract fun update(hike: HikeTable)


    /** DELETE HIKE */
    @Transaction
    open fun deleteHike(hikeId: Long) {
        delete(hikeId)
        deleteHikeParticipantJoin(hikeId)
        deleteHikeRouteJoin(hikeId)
    }

    @Query("DELETE FROM hike_participant_join  WHERE hikeId = :hikeId ")
    abstract fun deleteHikeParticipantJoin(hikeId: Long): Int

    @Query("DELETE FROM hike_route_join  WHERE hikeIdJoin = :hikeId ")
    abstract fun deleteHikeRouteJoin(hikeId: Long): Int

    @Delete
    abstract fun delete(hike: HikeTable)

    @Query("DELETE  FROM hikes where id = :idHike ")
    abstract fun delete(idHike: Long): Int

    @Query("DELETE FROM hikes")
    abstract fun dropTable()

}