package eu.mmassi.expensesmanager.di.app

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {

        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        requireNotNull(creator) {  }

        return creator.get() as T
    }
}