package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.HikeParticipantJoin
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.data.room.entity.ParticipantTable

@Dao
abstract class HikeDAO {


    @Transaction
    open fun createHike(hike: HikeTable, participant: ParticipantTable) {
        insert(hike)
        insertParticipant(participant)
        insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hike.id, participantId = participant.id))
    }


    @Transaction
    open fun addUserToHikeGroup(hikeId: Long, participant: ParticipantTable) {
        insertParticipant(participant)
        insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hikeId, participantId = participant.id))
    }


    @Transaction
    open fun cashHike(hike: HikeTable, participant: List<ParticipantTable>) {
        insert(hike)
        participant.forEach {
            insertParticipant(it)
            insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hike.id, participantId = it.id))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertParticipant(vararg participant: ParticipantTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertHikeParticipantJoin(hikeParticipantJoin: HikeParticipantJoin)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg hikes: HikeTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(hikes: List<HikeTable>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(vararg hikes: HikeTable)

    @Query("SELECT * FROM hikes where id = :idHike ")
    abstract fun getById(idHike: Int): Single<HikeTable>

    @Update
    abstract fun update(hike: HikeTable)

    @Delete
    abstract fun delete(hike: HikeTable)

    @Query("DELETE FROM hikes")
    abstract fun dropTable()


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


    //RELATIVE
    @Query("SELECT * FROM hikes")
    abstract fun getHikes(): Single<List<HikeTable>>

    // get hikes by participant id
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM hikes INNER JOIN hike_participant_join ON hikes.id = hike_participant_join.hikeId WHERE hike_participant_join.participantId = :participantId")
    abstract fun getHikesByUserId(participantId: String): Single<List<HikeTable>>

    // get hikes by participant id and type Hike
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM hikes INNER JOIN hike_participant_join ON hikes.id = hike_participant_join.hikeId WHERE hike_participant_join.participantId = :participantId AND hikes.type = :type ")
    abstract fun getHikes(participantId: String, type: Int): Single<List<HikeTable>>

}