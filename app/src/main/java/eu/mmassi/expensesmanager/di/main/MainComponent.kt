package eu.mmassi.expensesmanager.di.main

import dagger.Subcomponent
import eu.mmassi.expensesmanager.ui.main.MainActivity
import eu.mmassi.expensesmanager.ui.main.posts.PostsFragment
import eu.mmassi.expensesmanager.ui.main.profile.ProfileFragment

@MainScope
@Subcomponent(modules = [
    MainModule::class,
    MainViewModelsModule::class
])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(postsFragment: PostsFragment)
}
