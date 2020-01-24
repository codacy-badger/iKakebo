package eu.mmassi.expensesmanager

import android.app.Activity
import android.app.Application
import eu.mmassi.expensesmanager.di.app.AppComponent
import eu.mmassi.expensesmanager.di.app.DaggerAppComponent

class ExpensesManagerApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().bindApplication(this).build()
    }
}

val Activity.appComponent: AppComponent
    get() = (application as ExpensesManagerApplication).appComponent
