package app.muneesh.githubtrendings.datalayer.remotedatasource

import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Muneesh on 2020-02-29.
 */
interface GitHubTrendingAPIService {

    @GET(Constants.REPO_END_POINT)
    fun getRepositories(): Observable<List<RepoData>>

    @GET(Constants.DEV_END_POINT)
    fun getDevelopers(): Observable<List<DeveloperData>>
}