package br.com.jose.sandbox.android.data.source

import androidx.lifecycle.LiveData
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User

/**
 * Main entry point for accessing user data.
 */
interface UsersDataSource {

    fun observeUsers(): LiveData<Result<List<User>>>

    suspend fun getUsers(): Result<List<User>>

    suspend fun getUsersSince(lastSync: Long): Result<List<User>>

    fun observeUser(userId: Long): LiveData<Result<User>>

    suspend fun getUser(userId: Long?): Result<User>

    suspend fun saveUser(user: User): Result<User>

    suspend fun updateUser(userId: Long, user: User): Result<User>

    suspend fun deleteUser(userId: Long)

}