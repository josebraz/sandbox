package br.com.jose.sandbox.android.data.source

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.jose.sandbox.android.BuildConfig
import br.com.jose.sandbox.android.data.entities.Result
import br.com.jose.sandbox.android.data.entities.User

class SyncRepository constructor(context: Context){

    companion object {
        const val USER_LAST_SYNC: String = "users-last-sync"
    }

    private val sync = context.getSharedPreferences(
        "${BuildConfig.APPLICATION_ID}.REMOTE_SYNC", Context.MODE_PRIVATE)

    var usersLastSyncLong
        get() = sync.getLong(USER_LAST_SYNC, 0L)
        set(lastSync) = with (sync.edit()) {
            putLong(USER_LAST_SYNC, lastSync)
            apply()
        }
}