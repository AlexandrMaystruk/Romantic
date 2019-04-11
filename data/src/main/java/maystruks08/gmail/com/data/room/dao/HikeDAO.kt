package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.data.room.entity.ParticipantTable

@Dao
interface HikeDAO {

    @Insert
    fun insert(vararg hikes: HikeTable)

    @Insert
    fun insert(hikes: List<HikeTable>)

    @Query("SELECT * FROM hikes")
    fun getHikes(): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes LEFT JOIN participants ON participants.hikeId = hikes.id  WHERE participants.userId IS :userId AND type IS :type")//todo fix query
    fun getHikesByType(userId: String, type: String): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes LEFT JOIN participants ON  participants.hikeId = hikes.id WHERE participants.userId IS :userId")//todo fix query
    fun getHikesByUserId(userId: String): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes where id = :idHike ")
    fun getById(idHike: Int): Single<HikeTable>

    @Insert
    fun addUserToHikeGroup(vararg participant: ParticipantTable): Completable

    @Update
    fun update(hike: HikeTable)

    @Delete
    fun delete(hike: HikeTable)

    @Query("DELETE FROM hikes")
    fun dropTable()

}