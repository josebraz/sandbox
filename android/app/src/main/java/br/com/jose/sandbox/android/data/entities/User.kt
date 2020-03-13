package br.com.jose.sandbox.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "local_id") val localId: Long?,
    @PrimaryKey @ColumnInfo(name = "server_id") var serverId: Long?,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "created_at") var createdAt: Long =  Date().time,
    @ColumnInfo(name = "modified_at") var modifiedAt: Long =  Date().time
)