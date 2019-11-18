package eu.mmassi.expensesmanager.di.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import eu.mmassi.expensesmanager.SessionManager
import eu.mmassi.expensesmanager.di.auth.AuthComponent
import eu.mmassi.expensesmanager.di.main.MainComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppSubComponents::class,
    AppModule::class,
    ViewModelFactoryModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent

    }

    val sessionManager: SessionManager

    fun authComponent(): AuthComponent.Factory
    fun mainComponent(): MainComponent.Factory
}
