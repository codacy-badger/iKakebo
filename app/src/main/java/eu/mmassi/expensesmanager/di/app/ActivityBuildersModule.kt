package eu.mmassi.expensesmanager.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mmassi.expensesmanager.di.auth.AuthModule
import eu.mmassi.expensesmanager.di.auth.AuthScope
import eu.mmassi.expensesmanager.di.auth.AuthViewModelsModule
import eu.mmassi.expensesmanager.di.main.MainFragmentBuildersModule
import eu.mmassi.expensesmanager.di.main.MainModule
import eu.mmassi.expensesmanager.di.main.MainScope
import eu.mmassi.expensesmanager.di.main.MainViewModelsModule
import eu.mmassi.expensesmanager.ui.auth.AuthActivity
import eu.mmassi.expensesmanager.ui.main.MainActivity

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [
        AuthViewModelsModule::class,
        AuthModule::class
    ])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [
        MainModule::class,
        MainViewModelsModule::class,
        MainFragmentBuildersModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity
}
