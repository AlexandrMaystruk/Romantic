package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import maystruks08.gmail.com.data.room.entity.HikeTable


@Dao
interface HikeDAO {

    @get:Query("SELECT * FROM hikes")
    val all: List<HikeTable>

    @Query("SELECT * FROM hikes where hike_type LIKE  :typeHike and id_firebase LIKE :firebaseId")
    fun getAllByType(typeHike: Int, firebaseId: String): List<HikeTable>

    @Query("SELECT * FROM hikes where id = :idHike ")
    fun getById(idHike: Int): HikeTable

    @Query("SELECT * FROM hikes where id_in_firestore = :idHikeFirestore AND hike_type =:type")
    fun getByIdAndType(idHikeFirestore: String, type: Int): HikeTable

    @Query("DELETE FROM hikes")
    fun dropTable()

    @Insert
    fun insertAll(vararg hikes: HikeTable)

    @Insert
    fun insertAll(hikes: List<HikeTable>)

    @Delete
    fun delete(hike: HikeTable)

    @Update
    fun update(hike: HikeTable)
}