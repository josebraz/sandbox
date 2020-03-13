package br.com.jose.sandbox.android.data.source.remote

import androidx.room.Delete
import br.com.jose.sandbox.android.data.entities.User
import retrofit2.Call
import retrofit2.http.*

interface UsersService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/since/{since}")
    fun getUsersSince(@Path("since") lastSync: Long): List<User>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Long): User

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @PUT("users/{userId}")
    suspend fun upgradeUser(@Path("userId") userId: Long, @Body user: User): User

    @DELETE("users/{userId}")
    suspend fun destroyUser(@Path("userId") userId: Long)

}