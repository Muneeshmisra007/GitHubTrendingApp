package app.muneesh.githubtrendings.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.muneesh.githubtrendings.viewmodels.DeveloperViewModel
import app.muneesh.githubtrendings.viewmodels.RepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Muneesh on 2020-03-02.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DeveloperViewModel::class)
    internal abstract fun bindDeveloperViewModel(editPlaceViewModel: DeveloperViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    internal abstract fun bindRepoViewModel(editPlaceViewModel: RepoViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}