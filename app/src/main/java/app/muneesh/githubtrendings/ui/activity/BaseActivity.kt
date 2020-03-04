package app.muneesh.githubtrendings.ui.activity

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Muneesh on 2020-03-02.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    abstract fun getActivityLayout(): Int

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentAndroidInjector
    }

    fun loadImage(view: View, url: String?, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}