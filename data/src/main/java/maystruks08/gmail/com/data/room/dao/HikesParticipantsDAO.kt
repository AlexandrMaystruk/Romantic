package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import maystruks08.gmail.com.data.room.entity.HikesParticipantsTable

@Dao
interface HikesParticipantsDAO {

    @get:Query("SELECT * FROM hikes_participants")
    val allHikeParticipants: List<HikesParticipantsTable>

    @Insert
    fun insertAll(vararg hikesParticipantTables: HikesParticipantsTable)

    @Insert
    fun insert(hikesParticipant: HikesParticipantsTable)

    @Insert
    fun insert(hikesParticipant: List<HikesParticipantsTable>)

    //получаем все id учасников похода
    @Query("SELECT * FROM hikes_participants WHERE hikes_participants.id_hike_firestore LIKE :hikeIdFirestore")
    fun getListIdByHikeId(hikeIdFirestore: Int): List<HikesParticipantsTable>


    //получаем все id походов в котором есть даный учасник
    @Query("SELECT * FROM hikes_participants WHERE hikes_participants.id_participant LIKE :participantId")
    fun getListHikeIdByParticipantId(participantId: String): List<HikesParticipantsTable>

    @Query("DELETE FROM hikes_participants ")
    fun dropTable()


    @Query("DELETE FROM hikes_participants WHERE id_hike_firestore LIKE :hikeIdFirestore AND id_participant LIKE :participantId ")
    fun delete(hikeIdFirestore: String, participantId: String)

    @Delete
    fun delete(hikesParticipant: HikesParticipantsTable)

}

