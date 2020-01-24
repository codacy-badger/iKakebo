package eu.mmassi.expensesmanager.ui.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.mmassi.expensesmanager.SessionManager
import eu.mmassi.expensesmanager.models.Resource
import eu.mmassi.expensesmanager.models.User
import eu.mmassi.expensesmanager.ui.auth.AuthState
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    private val _authenticatedUser = MutableLiveData<Resource<User>>()
    val authenticatedUser = _authenticatedUser

    private val authStateObserver = { authState: AuthState ->
        _authenticatedUser.value = when (authState) {
            is AuthState.Authenticated -> {
                Resource.Success(authState.user)
            }
            is AuthState.Authenticating -> {
                Resource.Loading()
            }
            is AuthState.AuthenticationError, is AuthState.NotAuthenticated -> {
                Resource.Error("Not authenticated")
            }
        }
    }

    init {
        sessionManager.authState.observeForever(authStateObserver)
    }

    override fun onCleared() {
        sessionManager.authState.removeObserver(authStateObserver)
        super.onCleared()
    }
}
