package app.muneesh.githubtrendings.di.modules

import app.muneesh.githubtrendings.ui.GitHubTrendingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Muneesh on 2020-03-02.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
     abstract fun contributeGitHubTrendingActivity(): GitHubTrendingActivity

/*
    @ContributesAndroidInjector
    internal abstract fun contributeGitHubDeveloperFragment(): GitHubDeveloperFragment

    @ContributesAndroidInjector
    internal abstract fun contributeGitHubRepoFragment(): GitHubRepoFragment
*/

}