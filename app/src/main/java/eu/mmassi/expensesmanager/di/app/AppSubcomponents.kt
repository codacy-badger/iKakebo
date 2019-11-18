package eu.mmassi.expensesmanager.di.app

import dagger.Module
import eu.mmassi.expensesmanager.di.auth.AuthComponent
import eu.mmassi.expensesmanager.di.main.MainComponent

@Module(subcomponents = [
    AuthComponent::class,
    MainComponent::class
])
class AppSubComponents