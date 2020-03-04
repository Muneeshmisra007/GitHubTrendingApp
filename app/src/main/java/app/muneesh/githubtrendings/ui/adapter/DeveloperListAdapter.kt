package app.muneesh.githubtrendings.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.muneesh.githubtrendings.R
import app.muneesh.githubtrendings.databinding.RowGithubDeveloperBinding
import app.muneesh.githubtrendings.datalayer.localdatasource.tables.DeveloperData
import com.bumptech.glide.Glide

/**
 * Created by Muneesh on 2020-03-02.
 */
class DeveloperListAdapter(private val context: Context, private var list: List<DeveloperData>) :
    RecyclerView.Adapter<DeveloperListAdapter.DevViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowGithubDeveloperBinding.inflate(inflater, parent,false)
        return DevViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DevViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateAdapterData(list: List<DeveloperData>) {
        this.list=list
        notifyDataSetChanged()
    }

    class DevViewHolder constructor(var binding: RowGithubDeveloperBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(developerData: DeveloperData) {
            binding.devData = developerData
            binding.executePendingBindings()
            setImage(developerData, binding)
        }

        private fun setImage(developerData: DeveloperData, binding: RowGithubDeveloperBinding) {
            Glide.with(binding.root)
                .load(developerData.avatar)
                .centerCrop()
                .into(binding.imgAvatar)
        }
    }
}