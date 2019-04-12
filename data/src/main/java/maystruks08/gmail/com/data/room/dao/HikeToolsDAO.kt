package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import maystruks08.gmail.com.data.room.entity.HikeToolsTable

@Dao
interface HikeToolsDAO {


    @get:Query("SELECT * FROM hike_tools")
    val all: List<HikeToolsTable>

    @Query("SELECT * FROM hike_tools where hikeId = :hikeId ")
    fun getByHikeId(hikeId: String): List<HikeToolsTable>

    @Query("SELECT * FROM hike_tools where hikeId = :hikeId ")
    fun isExistHikeTool(hikeId: String): Boolean

    @Insert
    fun insertAll(tools: List<HikeToolsTable>)

    @Insert
    fun insert(vararg tools: HikeToolsTable)

    @Delete
    fun delete(tool: HikeToolsTable)

    @Update
    fun update(tool: HikeToolsTable)

    @Update
    fun update(tools: List<HikeToolsTable>)

    @Query("DELETE FROM hike_tools")
    fun dropTable()


}
