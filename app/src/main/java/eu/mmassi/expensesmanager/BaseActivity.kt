package eu.mmassi.expensesmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import eu.mmassi.expensesmanager.ui.auth.AuthActivity
import eu.mmassi.expensesmanager.ui.auth.AuthState
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager.authState.observe(this) { authState ->
            when (authState) {
                is AuthState.NotAuthenticated -> {
                    Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
                    navigateToLogin()
                }
                is AuthState.AuthenticationError -> {
                    Toast.makeText(this,
                        "Authentication error: ${authState.message}", Toast.LENGTH_LONG).show()
                    navigateToLogin()
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}
