package app.muneesh.githubtrendings.di

import android.app.Application
import androidx.room.Room
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.core.CoreUtils
import app.muneesh.githubtrendings.datalayer.localdatasource.GitHubTrendingDB
import app.muneesh.githubtrendings.datalayer.localdatasource.daos.RepositoryDao
import app.muneesh.githubtrendings.datalayer.remotedatasource.GitHubTrendingAPIService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.Binds
import dagger.android.AndroidInjectionModule


/**
 * Created by Muneesh on 2020-03-01.
 */

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    }

    @Provides
    @Singleton
    fun getHttpLoggingIntercepter(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): GitHubTrendingAPIService {
        return retrofit.create(GitHubTrendingAPIService::class.java)
    }

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): GitHubTrendingDB {
        return Room.databaseBuilder(context, GitHubTrendingDB::class.java, Constants.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun getAppDBDao(db: GitHubTrendingDB): RepositoryDao {
        return db.getLocalRepositoryDao()
    }
}