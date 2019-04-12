package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.HikeTable
import maystruks08.gmail.com.data.room.entity.ParticipantTable

@Dao
interface HikeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hikes: HikeTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hikes: List<HikeTable>)

    @Query("SELECT * FROM hikes")
    fun getHikes(): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes LEFT JOIN participants ON participants.hikeId = hikes.id  WHERE participants.userId IS :userId AND type IS :type")//todo fix query
    fun getHikesByType(userId: String, type: String): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes LEFT JOIN participants ON  participants.hikeId = hikes.id WHERE participants.userId IS :userId")//todo fix query
    fun getHikesByUserId(userId: String): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes where id = :idHike ")
    fun getById(idHike: Int): Single<HikeTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserToHikeGroup(vararg participant: ParticipantTable): Completable

    @Update
    fun update(hike: HikeTable)

    @Delete
    fun delete(hike: HikeTable)

    @Query("DELETE FROM hikes")
    fun dropTable()




    //need to upload if upload is null
    @Query("SELECT * FROM hikes WHERE upload IS NULL")
    fun getNotUploadHikes(): Single<List<HikeTable>>

    @Query("SELECT COUNT(*) FROM hikes WHERE upload IS NULL")
    fun getNotUploadHikesCount():  Single<Int>

    @Query("UPDATE hikes SET upload = :date WHERE id= :hikeId")
    fun setHikesUploaded(hikeId: Long, date: Long)


    // already updated = 1 , need to update = 0
    @Query("SELECT * FROM hikes WHERE updated IS 0")
    fun getNotUpdateHikes(): Single<List<HikeTable>>

    @Query("SELECT COUNT(*) FROM hikes WHERE updated IS 0")
    fun getNotUpdatedHikesCount(): Single<Int>

    @Query("UPDATE hikes SET updated = 0  WHERE id IS :hikeId")
    fun setNeedToUpdate(hikeId: Long)

    @Query("UPDATE hikes SET updated = 1  WHERE id IS :hikeId")
    fun setAlreadyUpdated(hikeId: Long)


}