package eu.mmassi.expensesmanager.di.app

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(
    private val viewModelsProvider: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {

        return viewModelsProvider[modelClass]?.get() as T?
            ?: throw IllegalArgumentException("Unknown viewModel class ${modelClass.simpleName}")
    }
}