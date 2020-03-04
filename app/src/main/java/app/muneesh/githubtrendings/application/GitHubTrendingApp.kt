package app.muneesh.githubtrendings.application

import android.app.Activity
import android.app.Application
import app.muneesh.githubtrendings.di.components.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-02-29.
 */
class GitHubTrendingApp : Application(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        Stetho.initializeWithDefaults(this)
    }


}