package maystruks08.gmail.com.data.room.dao


import androidx.room.*
import maystruks08.gmail.com.data.room.entity.ToolsTable

@Dao
interface ToolsDAO {


    @get:Query("SELECT * FROM tools")
    val all: List<ToolsTable>

    @Query("SELECT * FROM tools where hike_id = :hikeId ")
    fun getByHikeId(hikeId: String): List<ToolsTable>

    @Insert
    fun insertAll(tools: List<ToolsTable>)

    @Insert
    fun insert(vararg tools: ToolsTable)

    @Delete
    fun delete(tool: ToolsTable)

    @Query("DELETE FROM tools")
    fun dropTable()

    @Update
    fun update(tool: ToolsTable)

    @Update
    fun update(tools: List<ToolsTable>)


}
