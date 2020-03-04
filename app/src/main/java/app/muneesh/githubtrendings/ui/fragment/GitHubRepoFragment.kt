package app.muneesh.githubtrendings.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.muneesh.githubtrendings.R
import app.muneesh.githubtrendings.core.CoreUtils
import app.muneesh.githubtrendings.databinding.FragmentGithubrepoListBinding
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import app.muneesh.githubtrendings.di.viewmodel.ViewModelFactory
import app.muneesh.githubtrendings.ui.adapter.RepoListAdapter
import app.muneesh.githubtrendings.viewmodels.RepoViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class GitHubRepoFragment : BaseFragment() {
    private lateinit var binding: FragmentGithubrepoListBinding
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var viewModel: RepoViewModel
    lateinit var adapter: RepoListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)
        binding.lifecycleOwner = this
        viewModel = factory.create(RepoViewModel::class.java)
        binding.viewModel = viewModel
        initializeView()
        subscribeViewModelData()
        getData()
        return binding.root
    }

    private fun subscribeViewModelData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            print(it.toString())
            populateData(it)
        })
    }

    private fun initializeView() {
        adapter = RepoListAdapter(activity!!.applicationContext, ArrayList(), this)
        binding.listRepo.layoutManager = LinearLayoutManager(activity)
        binding.listRepo.adapter = adapter
    }

    fun populateData(list: List<RepoData>) {
        adapter.updateAdapterData(list)
    }

    private fun getData() {
        if (CoreUtils.isConnectedToInternet(activity!!.applicationContext)) {
            viewModel.getRepos()
        } else {
            Toast.makeText(activity, getString(R.string.internet_check_message), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GitHubRepoFragment()
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_githubrepo_list
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.trending_repo)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun searchText(text: String?) {
        if (text == null || text.isEmpty()) {
            subscribeViewModelData()
        } else {
            viewModel.searchText(text)
            viewModel.getSearchLiveData().observe(viewLifecycleOwner, Observer {
                adapter.updateAdapterData(it)
            })
        }
    }
}
