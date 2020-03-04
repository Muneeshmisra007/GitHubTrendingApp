package app.muneesh.githubtrendings.di.components

import android.app.Application
import app.muneesh.githubtrendings.application.GitHubTrendingApp
import app.muneesh.githubtrendings.di.AppModule
import app.muneesh.githubtrendings.di.modules.ActivityModule
import app.muneesh.githubtrendings.di.modules.FragmentModule
import app.muneesh.githubtrendings.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Muneesh on 2020-03-01.
 */

@Component(
    modules = [AppModule::class, ViewModelModule::class,

        ActivityModule::class]
)
@Singleton
interface AppComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

     fun inject(instance: GitHubTrendingApp)
}