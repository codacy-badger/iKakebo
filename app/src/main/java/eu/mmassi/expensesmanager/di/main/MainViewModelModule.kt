package eu.mmassi.expensesmanager.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.mmassi.expensesmanager.di.app.ViewModelKey
import eu.mmassi.expensesmanager.ui.main.MainViewModel

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
