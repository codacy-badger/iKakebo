package eu.mmassi.expensesmanager.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import eu.mmassi.expensesmanager.SessionManager
import eu.mmassi.expensesmanager.models.Post
import eu.mmassi.expensesmanager.models.Resource
import eu.mmassi.expensesmanager.network.main.MainApi
import eu.mmassi.expensesmanager.ui.auth.AuthState
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    val posts = liveData<Resource<List<Post>>> {
        emit(Resource.Loading())
        val user = (sessionManager.authState.value as? AuthState.Authenticated)?.user
            ?: return@liveData
        Log.d(TAG, "Retrieving posts for user ${user.id}")
        try {
            emit(Resource.Success(mainApi.getPostsForUser(user.id)))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.toString()))
        }
    }

    companion object {

        private val TAG = PostsViewModel::class.java.name
    }
}
