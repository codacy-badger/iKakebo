package eu.mmassi.expensesmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.mmassi.expensesmanager.models.User
import eu.mmassi.expensesmanager.models.Resource
import eu.mmassi.expensesmanager.ui.auth.AuthState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState>
        get() = _authState

    fun authenticationStateChanged(authUser: Resource<User>) {
        when (authUser) {
            is Resource.Success -> _authState.value = AuthState.Authenticated(authUser.data)
            is Resource.Error -> _authState.value = AuthState.AuthenticationError(authUser.message)
            is Resource.Loading -> _authState.value = AuthState.Authenticating
        }
    }

    fun logOut() {
        _authState.value = AuthState.NotAuthenticated
    }
}
