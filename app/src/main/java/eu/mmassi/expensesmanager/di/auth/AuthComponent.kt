package eu.mmassi.expensesmanager.di.auth

import dagger.Subcomponent
import eu.mmassi.expensesmanager.ui.auth.AuthActivity

@AuthScope
@Subcomponent(modules = [
    AuthViewModelsModule::class,
    AuthModule::class
])
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AuthComponent
    }

    fun inject(authActivity: AuthActivity)
}