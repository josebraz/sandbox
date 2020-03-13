package br.com.jose.sandbox.android.data.source

import android.content.Context
import androidx.room.Room
import br.com.jose.sandbox.android.BuildConfig
import br.com.jose.sandbox.android.data.source.local.SandboxDatabase
import br.com.jose.sandbox.android.data.source.local.DatabaseConstants
import br.com.jose.sandbox.android.data.source.local.UsersLocalDataSource
import br.com.jose.sandbox.android.data.source.remote.UsersRemoteDataSource
import br.com.jose.sandbox.android.data.source.remote.UsersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RepositoryFactory {

    private var database: SandboxDatabase? = null
    private var retrofit: Retrofit? = null

    private var usersRepository: UsersRepository? = null
    private var syncRepository: SyncRepository? = null

    fun provideUsersRepository(context: Context): UsersRepository {
        synchronized(this) {
            return usersRepository ?: createUsersRepository(context)
        }
    }

    private fun provideSyncRepository(context: Context): SyncRepository {
        synchronized(this) {
            return syncRepository ?: SyncRepository(context)
        }
    }

    private fun createUsersRepository(context: Context): UsersRepository {
        val newRepo =
            DefaultUsersRepository(createUsersRemoteDataSource(),
                createUsersLocalDataSource(context),
                provideSyncRepository(context)
            )
        usersRepository = newRepo
        return newRepo
    }

    private fun createUsersRemoteDataSource(): UsersDataSource {
        val rep = retrofit ?: createRetrofit()
        val service = rep.create(UsersService::class.java)
        return UsersRemoteDataSource(service)
    }

    private fun createUsersLocalDataSource(context: Context): UsersDataSource {
        val db = database ?: createDatabase(context)
        val dao = db.userDao()
        return UsersLocalDataSource(dao)
    }

    private fun createDatabase(context: Context): SandboxDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SandboxDatabase::class.java, DatabaseConstants.DB_NAME
        ).build().also {
            database = it
        }
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.URL_SERVER}/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build().also {
                retrofit = it
            }
    }
}