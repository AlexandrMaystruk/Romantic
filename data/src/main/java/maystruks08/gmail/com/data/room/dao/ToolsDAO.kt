package maystruks08.gmail.com.data.room.dao


import androidx.room.*
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.ToolsTable

@Dao
interface ToolsDAO {


    @Query("SELECT * FROM tools")
    fun getTools(): Single<List<ToolsTable>>

    @Query("SELECT * FROM tools where id LIKE :id ")
    fun getById(id: String): Single<List<ToolsTable>>

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
