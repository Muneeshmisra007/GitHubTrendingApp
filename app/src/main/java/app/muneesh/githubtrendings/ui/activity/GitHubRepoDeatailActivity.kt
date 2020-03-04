package app.muneesh.githubtrendings.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.muneesh.githubtrendings.R
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.databinding.ActivityGitHubRepoDeatailBinding

class GitHubRepoDeatailActivity : BaseActivity() {

    lateinit var binding: ActivityGitHubRepoDeatailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())
        binding = DataBindingUtil.setContentView(this, getActivityLayout())
        intent?.apply {
            binding.repoData = intent.getParcelableExtra(Constants.REPO_EXTRA_KEY)
        }
        loadImage(binding.root, binding.repoData?.avatar, binding.imgAvatar)
    }

    override fun getActivityLayout(): Int {
        return R.layout.activity_git_hub_repo_deatail;
    }
}
