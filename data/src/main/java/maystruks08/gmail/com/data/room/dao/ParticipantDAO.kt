package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
interface ParticipantDAO {

    @Query("SELECT * FROM participants WHERE hikeId LIKE :hikeId ")
    fun getParticipantsByHikeId(hikeId: String): Single<List<ParticipantTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserToHikeGroup(vararg participant: ParticipantTable): Completable

    @Query("DELETE FROM participants WHERE hikeId LIKE :hikeId")
    fun removeGroup(hikeId: Long)

    @Delete
    fun delete(participant: ParticipantTable)

    @Delete
    fun delete(participants: List<ParticipantTable>): Single<Int>

    @Update
    fun update(participant: ParticipantTable)

    @Update
    fun update(participants: List<ParticipantTable>)


    ////////work with updates
    @Query("SELECT * FROM participants WHERE upload IS NULL")
    fun getNotUploadParticipants(): Single<List<ParticipantTable>>

    @Query("SELECT COUNT(*) FROM participants WHERE upload IS NULL")
    fun getNotUploadParticipantsCount(): Single<Int>

    @Query("UPDATE participants SET upload = :date  WHERE userId = :userId ")
    fun setParticipantUploaded(userId: String, date: Long)



    // already updated = 1 , need to update = 0
    @Query("SELECT * FROM participants WHERE updated IS 0")
    fun getNotUpdateParticipants(): Single<List<ParticipantTable>>

    @Query("SELECT COUNT(*) FROM participants WHERE updated IS 0")
    fun getNotUpdatedParticipantCount(): Single<Int>

    @Query("UPDATE participants SET updated = 0  WHERE userId IS :userId")
    fun setNeedToUpdate(userId: String)

    @Query("UPDATE participants SET updated = 1  WHERE userId IS :userId")
    fun setAlreadyUpdated(userId: String)
}


