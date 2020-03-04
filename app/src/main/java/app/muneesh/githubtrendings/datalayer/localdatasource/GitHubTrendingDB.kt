package app.muneesh.githubtrendings.datalayer.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.datalayer.localdatasource.daos.RepositoryDao
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData

/**
 * Created by Muneesh on 2020-02-29.
 */
@Database(entities = [RepoData::class, DeveloperData::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class GitHubTrendingDB : RoomDatabase() {

    abstract fun getLocalRepositoryDao(): RepositoryDao


}