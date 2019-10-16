package eu.mmassi.expensesmanager.di.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import eu.mmassi.expensesmanager.ExpensesManagerApplication

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class
])
interface AppComponent: AndroidInjector<ExpensesManagerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}