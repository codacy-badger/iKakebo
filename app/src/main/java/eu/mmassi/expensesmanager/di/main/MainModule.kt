package eu.mmassi.expensesmanager.di.main

import dagger.Module
import dagger.Provides
import eu.mmassi.expensesmanager.network.main.MainApi
import eu.mmassi.expensesmanager.ui.main.posts.PostsRecyclerAdapter
import retrofit2.Retrofit

@Module
object MainModule {

    @MainScope
    @JvmStatic
    @Provides
    fun providesMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @MainScope
    @JvmStatic
    @Provides
    fun providesAdapter() = PostsRecyclerAdapter()
}
