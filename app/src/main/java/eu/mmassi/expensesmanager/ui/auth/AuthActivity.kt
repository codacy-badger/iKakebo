package eu.mmassi.expensesmanager.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.bumptech.glide.RequestManager
import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.appComponent
import eu.mmassi.expensesmanager.di.app.ViewModelProviderFactory
import eu.mmassi.expensesmanager.ui.main.MainActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_auth.login_button
import kotlinx.android.synthetic.main.activity_auth.login_logo
import kotlinx.android.synthetic.main.activity_auth.progress_bar
import kotlinx.android.synthetic.main.activity_auth.user_id_input

class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.authComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        authViewModel = viewModelProviderFactory.create(AuthViewModel::class.java)

        login_button.setOnClickListener {
            attemptLogin()
        }

        setLogo()
        observeAuthUser()
    }

    private fun observeAuthUser() {
        authViewModel.authState.observe(this) { authState ->
            when (authState) {
                is AuthState.Authenticated -> {
                    Log.i(TAG, "Authenticated with user ${authState.user.email}")
                    progress_bar.visibility = View.GONE
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is AuthState.Authenticating -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is AuthState.AuthenticationError -> {
                    Log.i(TAG, "Authentication failed with error ${authState.message}")
                    Toast.makeText(this,
                        "Authentication failed with error ${authState.message}",
                        Toast.LENGTH_LONG).show()
                    progress_bar.visibility = View.GONE
                }
            }
        }
    }

    private fun attemptLogin() {
        val userId = user_id_input.text
        if (!TextUtils.isEmpty(userId)) {
            authViewModel.authenticateWithId(userId.toString().toInt())
        }
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }

    companion object {

        private val TAG = AuthActivity::class.java.name
    }
}
