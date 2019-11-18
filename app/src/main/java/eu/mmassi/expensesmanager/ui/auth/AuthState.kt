package eu.mmassi.expensesmanager.ui.auth

import eu.mmassi.expensesmanager.models.User

sealed class AuthState {
    data class Authenticated(val user: User) : AuthState()
    object NotAuthenticated : AuthState()
    object Authenticating : AuthState()
    data class AuthenticationError(val message: String) : AuthState()
}
