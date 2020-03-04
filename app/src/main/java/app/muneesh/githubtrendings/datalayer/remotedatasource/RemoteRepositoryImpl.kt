package app.muneesh.githubtrendings.datalayer.remotedatasource

import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-02-29.
 */
class RemoteRepositoryImpl @Inject constructor(private val apiService: GitHubTrendingAPIService){

     fun getRepositories(): Observable<List<RepoData>> {
        return apiService.getRepositories()
    }

     fun getDevelopers(): Observable<List<DeveloperData>> {
        return apiService.getDevelopers()
    }

}