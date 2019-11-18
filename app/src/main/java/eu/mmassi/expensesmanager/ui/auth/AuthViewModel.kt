package eu.mmassi.expensesmanager.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.mmassi.expensesmanager.SessionManager
import eu.mmassi.expensesmanager.models.Resource
import eu.mmassi.expensesmanager.network.auth.AuthApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    val authState: LiveData<AuthState>
        get() = sessionManager.authState

    fun authenticateWithId(userId: Int) {
        sessionManager.authenticationStateChanged(Resource.Loading())

        viewModelScope.launch {
            try {
                sessionManager.authenticationStateChanged(Resource.Success(authApi.getUser(userId)))
            } catch (exception: Exception) {
                sessionManager.authenticationStateChanged(Resource.Error(exception.toString()))
            }
        }
    }
}
