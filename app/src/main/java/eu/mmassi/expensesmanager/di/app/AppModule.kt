package eu.mmassi.expensesmanager.di.app

import android.app.Application
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import eu.mmassi.expensesmanager.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @JvmStatic
    @Provides
    fun providesRequestOptions() =
        RequestOptions().placeholder(R.drawable.white_background).error(R.drawable.white_background)

    @Singleton
    @JvmStatic
    @Provides
    fun providesRequestManager(application: Application, requestOptions: RequestOptions) =
        Glide.with(application).setDefaultRequestOptions(requestOptions)

    @Singleton
    @JvmStatic
    @Provides
    fun providesAppDrawable(application: Application) =
        ContextCompat.getDrawable(application, R.drawable.logo)!!

    @Singleton
    @JvmStatic
    @Provides
    fun providesRetrofitInstance(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
}
