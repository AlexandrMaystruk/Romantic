package maystruks08.gmail.com.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single
import maystruks08.gmail.com.data.room.entity.ParticipantTable
import maystruks08.gmail.com.data.room.entity.UserTable

@Dao
interface UserDAO {

    @Insert
    fun insert(vararg users: UserTable): Completable

    @Insert
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


