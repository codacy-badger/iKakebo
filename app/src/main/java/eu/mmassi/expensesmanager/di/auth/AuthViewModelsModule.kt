package eu.mmassi.expensesmanager.di.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.mmassi.expensesmanager.di.app.ViewModelKey
import eu.mmassi.expensesmanager.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelsModule {

    @AuthScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}
