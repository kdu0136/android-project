package kim.dongun.paletteapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kim.dongun.paletteapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(),
    View.OnTouchListener {
    private var bitmap: Bitmap? = null
        set(value) {
            field = value
            value?.run {
                setTextViewColor(bitmap = value)
            }
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        viewBinding.imageView.setOnTouchListener(this@MainActivity)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionHelper.REQUEST_GALLERY_CODE -> {
                if (PermissionHelper.resultRequestPermissions(results = grantResults))
                    loadImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            glideRequestManager
                .load(data?.data)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ) = false

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        bitmap = (resource as? BitmapDrawable)?.bitmap
                        return false
                    }
                })
                .into(viewBinding.imageView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loadImageBtn -> {
                loadImage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadImage() {
        if (galleryPermissionCheck()) {
            val intent = Intent()
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 101)
        }
    }

    private fun galleryPermissionCheck(): Boolean {
        val hasGalleryPermission = PermissionHelper.hasPermission(
            context = this,
            permissions = PermissionHelper.galleryPermissions
        )
        return if (!hasGalleryPermission) {
            PermissionHelper.requestGalleryPermissions(context = this)
            false
        } else true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v != null && event != null) {
            when (v.id) {
                R.id.imageView -> {
                    if (bitmap != null && (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE)) {
//                        PrintLog.d("imageView", "touch (${event.x}, ${event.y})")
//                        PrintLog.d("bitmap size", "w: ${bitmap!!.width}, h: ${bitmap!!.height}")
                        val pixel = bitmap!!.getPixel(event.x.toInt(), event.y.toInt())

                        val r = Color.red(pixel)
                        val g = Color.green(pixel)
                        val b = Color.blue(pixel)

                        viewBinding.spoidColor.setBackgroundColor(Color.rgb(r, g, b))
                    }
                }
            }
        }
        return true
    }

    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette =
        Palette.from(bitmap).maximumColorCount(32).generate()

    // Generate palette asynchronously and use it on a different
    // thread using onGenerated()
    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            // Use generated instance
        }
    }

    // Set the background and text colors of a toolbar given a
    // bitmap image to match
    fun setTextViewColor(bitmap: Bitmap) {
        val palette = createPaletteSync(bitmap = bitmap)

        palette.lightVibrantSwatch?.rgb?.run {
            viewBinding.view1.setBackgroundColor(this)
        }
        palette.vibrantSwatch?.rgb?.run {
            viewBinding.view2.setBackgroundColor(this)
        }
        palette.darkVibrantSwatch?.rgb?.run {
            viewBinding.view3.setBackgroundColor(this)
        }
        palette.lightMutedSwatch?.rgb?.run {
            viewBinding.view4.setBackgroundColor(this)
        }
        palette.mutedSwatch?.rgb?.run {
            viewBinding.view5.setBackgroundColor(this)
        }
        palette.darkMutedSwatch?.rgb?.run {
            viewBinding.view6.setBackgroundColor(this)
        }
    }
} 