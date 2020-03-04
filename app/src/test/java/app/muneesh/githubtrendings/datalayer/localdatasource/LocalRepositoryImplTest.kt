package app.muneesh.githubtrendings.datalayer.localdatasource

import app.muneesh.githubtrendings.datalayer.localdatasource.daos.RepositoryDao
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.powermock.api.mockito.PowerMockito
import javax.inject.Inject


/**
 * Created by Muneesh on 2020-03-03.
 */
@RunWith(PowerMockRunner::class)
class LocalRepositoryImplTest {
    @Rule
    var mockitoRule = MockitoJUnit.rule()


    @InjectMocks
    lateinit var repositoryDao: RepositoryDao

    @InjectMocks
    lateinit var localRepositoryImpl: LocalRepositoryImpl

    @Before
    fun setUp() {
        PowerMockito.mockStatic(RepositoryDao::class.java)
        initMocks(this);

    }

    @Test
    fun deleteAllRepos() {
        PowerMockito.mockStatic(RepositoryDao::class.java)
        localRepositoryImpl.deleteAllRepos()
        verify(repositoryDao, times(1)).deleteAllRepos()
    }


    @Test
    fun deleteAllDevs() {
        PowerMockito.mockStatic(RepositoryDao::class.java)
        localRepositoryImpl.deleteAllDevs()
        verify(repositoryDao, times(1)).deleteAllDevs()
    }
}