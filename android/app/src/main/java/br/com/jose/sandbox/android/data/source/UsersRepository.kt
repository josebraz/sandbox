package br.com.jose.sandbox.android.data.source

import androidx.lifecycle.LiveData
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User

/**
 * Interface to the user data layer.
 */
interface UsersRepository {

    fun observeUsers(): LiveData<Result<List<User>>>

    fun observeUser(userId: Long): LiveData<Result<User>>

    suspend fun getUsers(forceUpdate: Boolean = false): Result<List<User>>

    suspend fun refreshUsers()

    suspend fun getUser(userId: Long, forceUpdate: Boolean = false): Result<User>

    suspend fun refreshUser(userId: Long)

    suspend fun saveUser(user: User)

    suspend fun updateUser(userId: Long, user: User)

    suspend fun deleteUser(userId: Long)

}