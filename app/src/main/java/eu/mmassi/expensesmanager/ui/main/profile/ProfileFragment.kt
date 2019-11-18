package eu.mmassi.expensesmanager.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import dagger.android.support.DaggerFragment

import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.di.app.ViewModelProviderFactory
import eu.mmassi.expensesmanager.models.User
import eu.mmassi.expensesmanager.models.Resource
import kotlinx.android.synthetic.main.fragment_profile.email
import kotlinx.android.synthetic.main.fragment_profile.username
import kotlinx.android.synthetic.main.fragment_profile.website
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = viewModelProviderFactory.create(ProfileViewModel::class.java)

        profileViewModel.authenticatedUser.removeObservers(viewLifecycleOwner)
        profileViewModel.authenticatedUser.observe(viewLifecycleOwner) { userResource ->
            when (userResource) {
                is Resource.Success -> {
                    setUserDetails(userResource.data)
                }
                is Resource.Error -> {
                    setError(userResource.message)
                }
            }
        }
    }

    private fun setUserDetails(user: User) {
        email.text = user.email
        username.text = user.username
        website.text = user.website
    }

    private fun setError(message: String?) {
        email.text = message
        username.text = getString(R.string.error)
        website.text = getString(R.string.error)
    }
}
