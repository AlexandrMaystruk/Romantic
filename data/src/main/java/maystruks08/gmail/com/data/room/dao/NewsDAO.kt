package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.NewsTable

@Dao
interface NewsDAO {

    @Query("SELECT * FROM news")
    fun getNews(): Single<List<NewsTable>>

    @Insert
    fun insertAll(vararg news: NewsTable)

    @Delete
    fun delete(news: NewsTable)

    @Query("DELETE FROM news")
    fun dropTable()

    @Update
    fun update(news: NewsTable)
}
