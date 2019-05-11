package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.HikeParticipantJoin
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
abstract class ParticipantDAO {

    @Transaction
    open fun addUserToHikeGroup(hikeId: Long, participant: ParticipantTable) {
        addParticipant(participant)
        insertHikeParticipantJoin(HikeParticipantJoin(hikeId = hikeId, participantId = participant.id))
    }

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


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHikeParticipantJoin(hikeParticipantJoin: HikeParticipantJoin)

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
}


