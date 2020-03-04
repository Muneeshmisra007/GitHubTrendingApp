package app.muneesh.githubtrendings.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.muneesh.githubtrendings.core.callbacks.OnItemClickListener
import app.muneesh.githubtrendings.databinding.RowGithubDeveloperBinding
import app.muneesh.githubtrendings.databinding.RowGithubRepoBinding
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.RepoData
import com.bumptech.glide.Glide

/**
 * Created by Muneesh on 2020-03-02.
 */
class RepoListAdapter(
    private val context: Context, private var list: List<RepoData>,
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowGithubRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

    class RepoViewHolder(
        private var binding: RowGithubRepoBinding,
        private var listener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(repoData: RepoData) {
            binding.repoData = repoData
            binding.listener = listener

            setImage(repoData, binding)
            binding.executePendingBindings()
        }

        private fun setImage(repoData: RepoData, binding: RowGithubRepoBinding) {
            Glide.with(binding.root)  //2
                .load(repoData.avatar) //3
                .centerCrop() //4
                .into(binding.imgAvatar)
        }
    }

    fun updateAdapterData(list: List<RepoData>) {
        this.list = list
        notifyDataSetChanged()
    }
}