package br.com.jose.sandbox.android.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User
import br.com.jose.sandbox.android.data.source.SyncRepository
import br.com.jose.sandbox.android.data.source.UsersDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

/**
 * Implementation of the users data source from remote server
 */
class UsersRemoteDataSource internal constructor(
    private val usersService: UsersService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UsersDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> = liveData(ioDispatcher) {
        return@liveData try {
            emit(Result.Success(usersService.getUsers()))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(usersService.getUsers())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUsersSince(lastSync: Long): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(usersService.getUsersSince(lastSync))
        } catch (e: Exception) {
            Result.Error(e)
        }

    }

    override fun observeUser(userId: Long): LiveData<Result<User>> = liveData(ioDispatcher) {
        return@liveData try {
            emit(Result.Success(usersService.getUserById(userId)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getUser(userId: Long?): Result<User> = withContext(ioDispatcher) {
        return@withContext try {
            if (userId == null) {
                Result.Error(Exception("User id is null!"))
            } else {
                Result.Success(usersService.getUserById(userId))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveUser(user: User) = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(usersService.createUser(user))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateUser(userId: Long, user: User): Result<User> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(usersService.upgradeUser(userId, user))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteUser(userId: Long) {
        usersService.destroyUser(userId)
    }

}