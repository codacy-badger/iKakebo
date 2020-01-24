package eu.mmassi.expensesmanager

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class ExpensesManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }
}
