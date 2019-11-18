package eu.mmassi.expensesmanager.di.auth

import dagger.Module
import dagger.Provides
import eu.mmassi.expensesmanager.network.auth.AuthApi
import retrofit2.Retrofit

@Module
object AuthModule {

    @JvmStatic
    @Provides
    @AuthScope
    fun providesAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
