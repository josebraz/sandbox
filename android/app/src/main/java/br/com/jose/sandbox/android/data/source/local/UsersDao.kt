package br.com.jose.sandbox.android.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.jose.sandbox.android.data.entities.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun observeUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE local_id = :userLocalId")
    fun observeUserByLocalId(userLocalId: Long): LiveData<User>

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE server_id = :userServerId")
    suspend fun getUserByServerId(userServerId: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): User

    @Update
    suspend fun updateUser(user: User): User

    @Query("DELETE FROM users WHERE local_id = :userLocalId")
    suspend fun deleteUserByLocalId(userLocalId: Long)
}