package eu.mmassi.expensesmanager.di.app

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelsFactory(viewModelsProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
