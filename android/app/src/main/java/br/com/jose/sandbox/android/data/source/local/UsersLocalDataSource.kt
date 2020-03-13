package br.com.jose.sandbox.android.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User
import br.com.jose.sandbox.android.data.source.UsersDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
* Concrete implementation of a data source as a db.
*/
class UsersLocalDataSource internal constructor(
    private val usersDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> {
        return usersDao.observeUsers().map {
            Result.Success(it)
        }
    }

    override fun observeUser(userId: Long): LiveData<Result<User>> {
        return usersDao.observeUserByLocalId(userId).map {
            Result.Success(it)
        }
    }

    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(usersDao.getUsers())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUsersSince(lastSync: Long): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: Long?): Result<User> = withContext(ioDispatcher) {
        try {
            if (userId == null) {
                return@withContext Result.Error(Exception("User id is null!"))
            }
            val user = usersDao.getUserByServerId(userId)
            if (user != null) {
                return@withContext Result.Success(user)
            } else {
                return@withContext Result.Error(Exception("User not found!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveUser(user: User): Result<User> = withContext(ioDispatcher) {
        try {
            return@withContext Result.Success(usersDao.insertUser(user))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun updateUser(userId: Long, user: User): Result<User> = withContext(ioDispatcher) {
        try {
            return@withContext Result.Success(usersDao.updateUser(user))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteUser(userId: Long) = withContext(ioDispatcher) {
        usersDao.deleteUserByLocalId(userId)
    }

}
