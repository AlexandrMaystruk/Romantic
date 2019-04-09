package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.HikeTable


@Dao
interface HikeDAO {

    @Insert
    fun insertAll(vararg hikes: HikeTable)

    @Insert
    fun insertAll(hikes: List<HikeTable>)

    @Delete
    fun delete(hike: HikeTable)

    @Update
    fun update(hike: HikeTable)

    @Query("SELECT * FROM hikes")
    fun getHikes(): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes WHERE type IS :type")
    fun getHikesByType(type: String): Single<List<HikeTable>>


    //todo write query from diff table
    @Query("SELECT * FROM hikes WHERE id is :userId")
    fun getHikesByUserId(userId: String): Single<List<HikeTable>>

    @Query("SELECT * FROM hikes where id = :idHike ")
    fun getById(idHike: Int): Single<HikeTable>

    @Query("DELETE FROM hikes")
    fun dropTable()

}