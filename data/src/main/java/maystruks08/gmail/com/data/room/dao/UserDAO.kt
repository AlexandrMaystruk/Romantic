package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserTable): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listUsers: List<UserTable>): List<Long>

    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<UserTable>>

    @Query("SELECT * FROM users WHERE id LIKE :id ")
    fun getUserById(id: String): Single<UserTable>

    @Update
    fun update(user: UserTable)

    @Update
    fun update(users: List<UserTable>)

    @Delete
    fun delete(user: UserTable)

    @Delete
    fun delete(user: List<UserTable>): Single<Int>

    @Query("DELETE FROM users")
    fun dropTable()
}


