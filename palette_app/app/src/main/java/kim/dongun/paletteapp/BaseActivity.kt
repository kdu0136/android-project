package kim.dongun.paletteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var viewBinding: T
    protected val glideRequestManager: RequestManager by lazy { Glide.with(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::viewBinding.isInitialized)
            setContentView(viewBinding.root)
    }
}