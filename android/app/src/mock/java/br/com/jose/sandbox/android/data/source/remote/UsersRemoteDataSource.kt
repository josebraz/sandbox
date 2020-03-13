package br.com.jose.sandbox.android.data.source.remote

import androidx.lifecycle.MutableLiveData
import br.com.jose.sandbox.android.data.entities.User
import br.com.jose.sandbox.android.data.source.UsersDataSource
import java.util.LinkedHashMap

/**
 * Implementation of the users data source from remote server
 */
object UsersRemoteDataSource : UsersDataSource {

    private var USERS_SERVICE_DATA: LinkedHashMap<String, User> = LinkedHashMap()

    private val observableUsers = MutableLiveData<Result<List<User>>>()

}