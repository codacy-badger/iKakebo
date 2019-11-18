package eu.mmassi.expensesmanager.network.main

import eu.mmassi.expensesmanager.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("/posts")
    suspend fun getPostsForUser(@Query("userId") userId: Int): List<Post>
}
