package br.com.jose.sandbox.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.jose.sandbox.android.data.entities.User

@Database(entities = [User::class],
    version = DatabaseConstants.DB_VERSION
)
abstract class SandboxDatabase : RoomDatabase() {

    abstract fun userDao(): UsersDao

}