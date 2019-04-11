package maystruks08.gmail.com.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
interface ParticipantDAO {

    @Query("SELECT * FROM participants WHERE hikeId LIKE :hikeId ")
    fun getParticipantsByHikeId(hikeId: String): Single<List<ParticipantTable>>

    @Insert
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
}


