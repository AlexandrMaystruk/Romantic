package maystruks08.gmail.com.data.sources.room.dao

import androidx.room.*
import maystruks08.gmail.com.data.sources.room.entity.NewsTable

@Dao
interface NewsDAO {

    @get:Query("SELECT * FROM news")
    val all: List<NewsTable>

    @Insert
    fun insertAll(vararg news: NewsTable)

    @Delete
    fun delete(news: NewsTable)

    @Query("DELETE FROM news")
    fun dropTable()


    @Update
    fun update(news: NewsTable)
}
