package br.com.jose.sandbox.android.data.source

import androidx.lifecycle.LiveData
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Default implementation of [UsersRepository]. Single entry point for managing users' data.
 */
class DefaultUsersRepository(
    private val usersRemoteDataSource: UsersDataSource,
    private val usersLocalDataSource: UsersDataSource,
    private val syncRepository: SyncRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {

    override fun observeUsers(): LiveData<Result<List<User>>> {
        return usersLocalDataSource.observeUsers()
    }

    override fun observeUser(userId: Long): LiveData<Result<User>> {
        return usersLocalDataSource.observeUser(userId)
    }

    override suspend fun getUsers(forceUpdate: Boolean): Result<List<User>> {
        if (forceUpdate) {
            try {
                updateUsersFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return usersLocalDataSource.getUsers()
    }

    override suspend fun refreshUsers() {
        updateUsersFromRemoteDataSource()
    }

    override suspend fun getUser(userId: Long, forceUpdate: Boolean): Result<User> {
        if (forceUpdate) {
            updateUserFromRemoteDataSource(userId)
        }
        return usersLocalDataSource.getUser(userId)
    }

    override suspend fun refreshUser(userId: Long) {
        updateUserFromRemoteDataSource(userId)
    }

    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userId: Long, user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: Long) {
        TODO("Not yet implemented")
    }

    private suspend fun updateUsersFromRemoteDataSource(): Unit = withContext(ioDispatcher) {
        val remoteUsersResult = usersRemoteDataSource.getUsersSince(syncRepository.usersLastSyncLong)

        if (remoteUsersResult is Result.Success) {
            remoteUsersResult.data.forEach { remoteUser ->
                saveRemoteUser(remoteUser)
                syncRepository.usersLastSyncLong = remoteUser.modifiedAt
            }
        } else if (remoteUsersResult is Result.Error) {
            throw remoteUsersResult.exception
        }
    }

    private suspend fun updateUserFromRemoteDataSource(userId: Long): Unit = withContext(ioDispatcher) {
        val remoteUserResult = usersRemoteDataSource.getUser(userId)

        if (remoteUserResult is Result.Success) {
            saveRemoteUser(remoteUserResult.data)
        } else if (remoteUserResult is Result.Error) {
            throw remoteUserResult.exception
        }
    }

    private suspend fun saveRemoteUser(remoteUser: User) {
        val localUser = usersLocalDataSource.getUser(remoteUser.serverId)
        if (localUser is Result.Success) { // Update user
            localUser.data.run {
                firstName = remoteUser.firstName
                lastName = remoteUser.lastName
                modifiedAt = remoteUser.modifiedAt
                usersLocalDataSource.saveUser(this)
            }
        } else { // Create user
            usersLocalDataSource.saveUser(remoteUser)
        }
    }

}