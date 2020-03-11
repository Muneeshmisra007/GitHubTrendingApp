package app.muneesh.githubtrendings.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.muneesh.githubtrendings.datalayer.GitHubRepository
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Muneesh on 2020-03-03.
 */
class RepoViewModel @Inject constructor(private var repository: GitHubRepository) : ViewModel() {
    private var repoData = MutableLiveData<List<RepoData>>()
    private var searchData = MutableLiveData<List<RepoData>>()
    var isLoading = MutableLiveData<Boolean>()
    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getRepos() {
        isLoading.value = true
        var disposable: Disposable = repository.getRemoteRepositories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                isLoading.value = false
            }
            .doOnError {
                repoData.value = ArrayList()
            }
            .subscribe {
                repoData.value = it

            }
        compositeDisposable.add(disposable)
    }

    fun getLiveData(): LiveData<List<RepoData>> {
        return repoData
    }

    fun getSearchLiveData(): LiveData<List<RepoData>> {
        return searchData;
    }

    fun searchText(text: String) {
        var disposable = repository.getRepoBySearch(text).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
            }
            .doOnError {
                repoData.value = Collections.emptyList()
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