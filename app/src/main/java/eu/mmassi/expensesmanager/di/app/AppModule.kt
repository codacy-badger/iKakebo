package eu.mmassi.expensesmanager.di.app

import android.app.Application
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import eu.mmassi.expensesmanager.R

@Module
class AppModule {

    @Module
    companion object {

        @JvmStatic @Provides
        fun providesRequestOptions() =
            RequestOptions().placeholder(R.drawable.white_background).error(R.drawable.white_background)

        @JvmStatic @Provides
        fun providesRequestManager(application: Application, requestOptions: RequestOptions) =
            Glide.with(application).setDefaultRequestOptions(requestOptions)

        @JvmStatic @Provides
        fun providesAppDrawable(application: Application) =
            ContextCompat.getDrawable(application, R.drawable.logo)!!
    }
}