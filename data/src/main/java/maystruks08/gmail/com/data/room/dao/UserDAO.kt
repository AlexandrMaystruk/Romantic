package maystruks08.gmail.com.data.room.dao

import androidx.room.*
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<UserTable>>

    @Query("SELECT * FROM users WHERE users.id LIKE :id ")
    fun getUserById(id: String): Single<UserTable>

    @Insert
    fun insert(vararg users: UserTable)

    @Insert
    fun insert(listUsers: List<UserTable>): List<Long>

    @Delete
    fun delete(user: UserTable)

    @Delete
    fun delete(user: List<UserTable>): Single<Int>

    @Query("DELETE FROM users")
    fun dropTable()

    @Update
    fun update(user: UserTable)

    @Update
    fun update(users: List<UserTable>)
}


