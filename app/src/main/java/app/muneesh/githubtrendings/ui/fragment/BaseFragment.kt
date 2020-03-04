package app.muneesh.githubtrendings.ui.fragment

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.core.CoreUtils
import app.muneesh.githubtrendings.core.callbacks.OnItemClickListener
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import app.muneesh.githubtrendings.ui.activity.GitHubRepoDeatailActivity
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-03-01.
 */
abstract class BaseFragment : Fragment(), OnItemClickListener {

    abstract fun getFragmentLayout(): Int

    abstract fun getTitle(context: Context): String

    abstract fun searchText(text: String?)

    override fun onClick(any: Any) {
        if (any is RepoData){
            startActivity(Intent(activity, GitHubRepoDeatailActivity::class.java).apply {
                putExtra(Constants.REPO_EXTRA_KEY, any)
            })
        }
    }
}