package eu.mmassi.expensesmanager.network.auth

import eu.mmassi.expensesmanager.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}
