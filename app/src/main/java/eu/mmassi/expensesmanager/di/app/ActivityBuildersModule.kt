package eu.mmassi.expensesmanager.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mmassi.expensesmanager.di.auth.AuthViewModelModule
import eu.mmassi.expensesmanager.ui.auth.AuthActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [
        AuthViewModelModule::class
    ])
    abstract fun contributeAuthActivity(): AuthActivity
}