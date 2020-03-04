package app.muneesh.githubtrendings.di.modules

import app.muneesh.githubtrendings.ui.fragment.GitHubDeveloperFragment
import app.muneesh.githubtrendings.ui.fragment.GitHubRepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Muneesh on 2020-03-03.
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
     abstract fun contributeGitHubDeveloperFragment(): GitHubDeveloperFragment

    @ContributesAndroidInjector
     abstract fun contributeGitHubRepoFragment(): GitHubRepoFragment

}