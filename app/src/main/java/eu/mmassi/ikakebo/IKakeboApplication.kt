package eu.mmassi.ikakebo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class IKakeboApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }
}
