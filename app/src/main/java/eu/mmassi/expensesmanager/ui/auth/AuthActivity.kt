package eu.mmassi.expensesmanager.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.di.app.ViewModelProviderFactory
import eu.mmassi.expensesmanager.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_auth.login_button
import kotlinx.android.synthetic.main.activity_auth.login_logo
import javax.inject.Inject

class AuthActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel::class.java)

        setLogo()

        login_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}
