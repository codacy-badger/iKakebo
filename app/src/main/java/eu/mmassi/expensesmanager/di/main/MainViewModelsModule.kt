package eu.mmassi.expensesmanager.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.mmassi.expensesmanager.di.app.ViewModelKey
import eu.mmassi.expensesmanager.ui.main.posts.PostsViewModel
import eu.mmassi.expensesmanager.ui.main.profile.ProfileViewModel

@Module
abstract class MainViewModelsModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel
}
