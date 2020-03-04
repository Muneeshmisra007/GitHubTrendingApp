package app.muneesh.githubtrendings.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import app.muneesh.githubtrendings.R
import app.muneesh.githubtrendings.core.CoreUtils
import app.muneesh.githubtrendings.databinding.FragmentGitHubDeveloperBinding
import app.muneesh.githubtrendings.datalayer.GitHubRepository
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.di.viewmodel.ViewModelFactory
import app.muneesh.githubtrendings.ui.adapter.DeveloperListAdapter
import app.muneesh.githubtrendings.viewmodels.DeveloperViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-03-01.
 */
class GitHubDeveloperFragment : BaseFragment() {


    private lateinit var binding: FragmentGitHubDeveloperBinding
    private lateinit var adapter: DeveloperListAdapter;

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: DeveloperViewModel
    @Inject
    lateinit var repos: GitHubRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)
        binding.lifecycleOwner = this
        viewModel = factory.create(DeveloperViewModel::class.java)
        binding.viewModel = viewModel
        initializeView()
        subscribeViewModel()
        getData()
        return binding.root
    }

    private fun initializeView() {
        adapter = DeveloperListAdapter(activity!!.applicationContext, ArrayList())
        binding.listDeveloper.adapter = adapter
    }

    fun populateData(list: List<DeveloperData>) {
        adapter.updateAdapterData(list)
    }

    private fun subscribeViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            populateData(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = GitHubDeveloperFragment()
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_git_hub_developer
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.trending_developer)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    fun getData() {
        if (CoreUtils.isConnectedToInternet(activity!!.applicationContext)) {
            viewModel.getDeveloperData()
        } else {
            Toast.makeText(activity, getString(R.string.internet_check_message), Toast.LENGTH_LONG)
                .show()
        }

    }

    override fun searchText(text: String?) {
        if (text == null || text.isEmpty()) {
            subscribeViewModel()
        } else {
            viewModel.getSearchData(text)
            viewModel.getSearchLiveData().observe(viewLifecycleOwner,
                Observer {
                    adapter.updateAdapterData(it)
                })
        }
    }
}