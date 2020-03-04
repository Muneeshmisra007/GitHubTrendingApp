package app.muneesh.githubtrendings.datalayer.localdatasource

import app.muneesh.githubtrendings.datalayer.localdatasource.daos.RepositoryDao
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-02-29.
 */
class LocalRepositoryImpl @Inject constructor(private val repositoryDao: RepositoryDao) {

    fun insertRepositories(trendingRepos: List<RepoData>) {
        repositoryDao.insertRepositories(trendingRepos)
    }

    fun getRepos(): Observable<List<RepoData>> {
        return repositoryDao.getRepos()
    }

    fun getRepoById(id: Int): Observable<RepoData> {
        return repositoryDao.getRepoById(id)
    }

    fun getRepoBySearch(search: String): Observable<List<RepoData>> {
       return repositoryDao.getRepoBySearch(search)
    }

    fun deleteAllRepos(): Int {
        return repositoryDao.deleteAllRepos()
    }

    fun insertDevs(trendingDevs: List<DeveloperData>) {
        repositoryDao.insertDevs(trendingDevs)
    }

    fun getDevs(): Observable<List<DeveloperData>> {
        return repositoryDao.getDevs()
    }

    fun deleteAllDevs() {
        repositoryDao.deleteAllDevs()
    }

    fun getDevsBySearch(search: String): Observable<List<DeveloperData>>{
        return repositoryDao.getDevsBySearch(search)
    }

}