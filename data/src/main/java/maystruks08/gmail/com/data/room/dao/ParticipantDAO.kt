package maystruks08.gmail.com.data.room.dao


import androidx.room.*
import maystruks08.gmail.com.data.room.entity.ParticipantTable

@Dao
interface ParticipantDAO {


    @get:Query("SELECT * FROM participants")
    val allParticipants: List<ParticipantTable>


    @Query("SELECT * FROM participants WHERE participants.id LIKE :id ")
    fun getParticipantById(id: String): ParticipantTable

    @Insert
    fun insertAll(vararg users: ParticipantTable)

    @Insert
    fun insertList(listUsers: List<ParticipantTable>): List<Long>

    @Delete
    fun delete(user: ParticipantTable)

    @Delete
    fun delete(user: List<ParticipantTable>): Int

    @Query("DELETE FROM participants")
    fun dropTable()

    @Update
    fun update(user: ParticipantTable)

    @Update
    fun update(users: List<ParticipantTable>)


}


