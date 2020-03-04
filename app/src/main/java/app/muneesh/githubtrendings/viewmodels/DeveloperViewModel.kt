package app.muneesh.githubtrendings.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.muneesh.githubtrendings.datalayer.GitHubRepository
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-03-02.
 */
class DeveloperViewModel @Inject constructor(private var repos: GitHubRepository) : ViewModel() {

    private var developerData = MutableLiveData<List<DeveloperData>>()
    private var searchData = MutableLiveData<List<DeveloperData>>()
    var isLoading = MutableLiveData<Boolean>()
    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getDeveloperData() {
        isLoading.value = true
        val disposable=   repos.getRemoteDevelopers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                isLoading.value = false
            }
            .doOnError {
                isLoading.value = false
                developerData.value = ArrayList<DeveloperData>()
            }
            .subscribe {
                developerData.value = it
            }
        compositeDisposable.add(disposable)
    }


    fun getLiveData(): LiveData<List<DeveloperData>> {
        return developerData
    }

    fun getSearchLiveData(): LiveData<List<DeveloperData>> {
        return searchData
    }

    @SuppressLint("CheckResult")
    fun getSearchData(search: String) {
        val disposable = repos.getDevsBySearch(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe {
                searchData.value = it
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}