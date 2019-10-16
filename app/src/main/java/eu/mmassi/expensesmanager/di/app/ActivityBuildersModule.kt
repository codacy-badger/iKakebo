package eu.mmassi.expensesmanager.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mmassi.expensesmanager.AuthActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity
}