package app.muneesh.githubtrendings.datalayer.localdatasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Muneesh on 2020-03-02.
 */
class TestObserver<T> : Observer<T> {

    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }

}