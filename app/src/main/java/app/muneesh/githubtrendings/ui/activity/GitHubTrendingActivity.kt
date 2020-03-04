package app.muneesh.githubtrendings.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.muneesh.githubtrendings.R
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.databinding.ActivityGitHubTrendingBinding
import app.muneesh.githubtrendings.ui.activity.BaseActivity
import app.muneesh.githubtrendings.ui.adapter.GitHubTabAdapter
import app.muneesh.githubtrendings.ui.fragment.BaseFragment
import app.muneesh.githubtrendings.ui.fragment.GitHubDeveloperFragment
import app.muneesh.githubtrendings.ui.fragment.GitHubRepoFragment
import dagger.android.AndroidInjection

class GitHubTrendingActivity : BaseActivity(), SearchView.OnQueryTextListener{

    private lateinit var viewPager: ViewPager
    private lateinit var binding: ActivityGitHubTrendingBinding
    private lateinit var list: List<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(getActivityLayout())
        binding = DataBindingUtil.setContentView(this, getActivityLayout())
        initialization()
    }

    private fun initialization() {
        viewPager = binding.viewPager
        viewPager.adapter =
            GitHubTabAdapter(this, supportFragmentManager, Constants.TWO_TABS, getFragmentList())
        val tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getString(R.string.app_name))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getFragmentList(): List<Fragment> {
        list = listOf(
            GitHubRepoFragment.newInstance(),
            GitHubDeveloperFragment.newInstance()
        )
        return list
    }

    override fun getActivityLayout(): Int {
        return R.layout.activity_git_hub_trending
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //when text searched
        sendText(query)
        return true
    }

    private fun sendText(query: String?) {
        (list.get(viewPager.currentItem) as BaseFragment).searchText(query)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //when text is cleared
        if (newText == null || newText.isEmpty()) {
            sendText(newText)
        }
        return true
    }
}