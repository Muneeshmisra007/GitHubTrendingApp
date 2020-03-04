package app.muneesh.githubtrendings.datalayer.localdatasource.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import io.reactivex.Observable

/**
 * Created by Muneesh on 2020-03-02.
 */
@Dao
interface RepositoryDao {

    companion object {
        const val GET_REPOS = "Select * from ${Constants.REPO_TABLE}"
        const val GET_REPO_BY_ID = "Select * from ${Constants.REPO_TABLE} where id=:id"
        const val GET_REPOS_BY_SEARCH = "Select * from ${Constants.REPO_TABLE} " +
                "where name || author LIKE '%'|| :search || '%'"
        const val DELETE_REPOS = "Delete from ${Constants.REPO_TABLE}"

        const val GET_DEVS = "Select * from ${Constants.DEV_TABLE}"
        const val DELETE_DEVS = "Delete from ${Constants.DEV_TABLE}"
        const val GET_DEVS_BY_SEARCH = "Select * from ${Constants.DEV_TABLE} " +
                "where name || username LIKE '%'|| :search || '%'"
    }

    //Repos query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(trendingRepos: List<RepoData>)

    @Query(GET_REPOS)
    fun getRepos(): Observable<List<RepoData>>

    @Query(GET_REPO_BY_ID)
    fun getRepoById(id: Int): Observable<RepoData>

    @Query(GET_REPOS_BY_SEARCH)
    fun getRepoBySearch(search: String): Observable<List<RepoData>>

    @Query(DELETE_REPOS)
    fun deleteAllRepos(): Int

    //devs Query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevs(trendingDevs: List<DeveloperData>)

    @Query(GET_DEVS)
    fun getDevs(): Observable<List<DeveloperData>>

    @Query(DELETE_DEVS)
    fun deleteAllDevs(): Int

    @Query(GET_DEVS_BY_SEARCH)
    fun getDevsBySearch(search: String):Observable<List<DeveloperData>>

}