package eu.mmassi.expensesmanager.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mmassi.expensesmanager.ui.main.posts.PostsFragment
import eu.mmassi.expensesmanager.ui.main.profile.ProfileFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}
