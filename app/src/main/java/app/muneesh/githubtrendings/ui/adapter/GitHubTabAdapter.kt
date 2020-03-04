package app.muneesh.githubtrendings.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import app.muneesh.githubtrendings.core.Constants
import app.muneesh.githubtrendings.ui.fragment.BaseFragment

/**
 * Created by Muneesh on 2020-03-01.
 */
class GitHubTabAdapter(val context: Context, manager: FragmentManager, tabs: Int, private val list: List<Fragment>) :
    FragmentStatePagerAdapter(manager, tabs) {

    override fun getItem(position: Int): Fragment {
        return list[position]

    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (list[position] as BaseFragment).getTitle(context)
    }
}