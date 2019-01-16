package maystruks08.gmail.com.data.sources.room.dao

import androidx.room.*
import maystruks08.gmail.com.data.sources.room.entity.HikeToolsTable

@Dao
interface HikeToolsDAO {


    @get:Query("SELECT * FROM hike_tools")
    val all: List<HikeToolsTable>

    @Query("SELECT * FROM hike_tools where hike_id = :hikeId ")
    fun getByHikeId(hikeId: String): List<HikeToolsTable>


    @Query("SELECT * FROM hike_tools where hike_id = :hikeId ")
    fun isExistHikeTool(hikeId: String): Boolean


    @Query("DELETE FROM hike_tools")
    fun dropTable()


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


}
