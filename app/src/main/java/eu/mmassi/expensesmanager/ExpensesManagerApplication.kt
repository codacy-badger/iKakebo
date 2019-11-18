package eu.mmassi.expensesmanager

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import eu.mmassi.expensesmanager.di.app.DaggerAppComponent

class ExpensesManagerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().bindApplication(this).build()
}
