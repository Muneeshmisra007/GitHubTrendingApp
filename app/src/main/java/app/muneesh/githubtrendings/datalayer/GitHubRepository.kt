package app.muneesh.githubtrendings.datalayer

import app.muneesh.githubtrendings.datalayer.localdatasource.LocalRepositoryImpl
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import app.muneesh.githubtrendings.datalayer.remotedatasource.RemoteRepositoryImpl
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Muneesh on 2020-02-29.
 */
@Singleton
class GitHubRepository @Inject constructor(
    private val localRepository: LocalRepositoryImpl,
    private val remoteRepository: RemoteRepositoryImpl
) {
    fun getLocalRepos(): Observable<List<RepoData>> {
        return localRepository.getRepos()
    }

    fun getLocalRepoById(id: Int): Observable<RepoData> {
        return localRepository.getRepoById(id)
    }

    fun getRepoBySearch(search: String): Observable<List<RepoData>> {
        return localRepository.getRepoBySearch(search)
    }

    fun getLocalAllDevs(): Observable<List<DeveloperData>> {
        return localRepository.getDevs()

    }

    fun getDevsBySearch(search: String): Observable<List<DeveloperData>> {
        return localRepository.getDevsBySearch(search)
    }

    fun getRemoteRepositories(): Observable<List<RepoData>> {
        return remoteRepository.getRepositories()
            .subscribeOn(Schedulers.io())
            .flatMap {
                localRepository.deleteAllRepos()
                localRepository.insertRepositories(it)
                Observable.just(it)
            }
    }

    fun getRemoteDevelopers(): Observable<List<DeveloperData>> {
        return remoteRepository.getDevelopers()
            .subscribeOn(Schedulers.io())
            .flatMap {
                localRepository.deleteAllDevs()
                localRepository.insertDevs(it)
                Observable.just(it)
            }
    }
}