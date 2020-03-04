package app.muneesh.githubtrendings.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import app.muneesh.githubtrendings.datalayer.localdatasource.TestObserver
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit

/**
 * Created by Muneesh on 2020-03-02.
 */
class DeveloperViewModelTest{
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var classUnderTest: DeveloperViewModel

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Test
    fun `init method sets liveData value to empty list`() {
        val liveDataUnderTest = classUnderTest.getLiveData().testObserver()

        Truth.assert_()
            .that(liveDataUnderTest.observedValues)
            .isEqualTo(listOf(emptyList<DeveloperData>()))
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
