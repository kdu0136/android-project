package kim.dongun.paletteapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainViewModel : ViewModel() {
    private var bitmap: Bitmap? = null
        set(value) {
            value?.run {
                field = value
                createPaletteAsync(bitmap = value) { palette ->
                    spoidColors.value = getPaletteColors(palette = palette)
                }
            }
        }
    val imageListener: RequestListener<Drawable> = object : RequestListener<Drawable> {
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
            (resource as? BitmapDrawable)?.bitmap?.run {
                bitmap = this
            }
            return false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    var onTouchListener: View.OnTouchListener = View.OnTouchListener { _, event ->
        setSpoidColor(event = event)
        true
    }

    val spoidColor = MutableLiveData<Int>()
    val imageUrl = MutableLiveData<Any?>()
    val spoidColors: SingleLiveEvent<ArrayList<Int>> = SingleLiveEvent()

    private fun setSpoidColor(event: MotionEvent) {
        if (bitmap == null) return

        val x = event.x.toInt()
        val y = event.y.toInt()
        if ((event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE)
            && x in (0..bitmap!!.width) && y in (0..bitmap!!.height)
        ) {
            val pixel = bitmap!!.getPixel(event.x.toInt(), event.y.toInt())

            val r = Color.red(pixel)
            val g = Color.green(pixel)
            val b = Color.blue(pixel)

            spoidColor.value = Color.rgb(r, g, b)
        }
    }

    fun setImageUrl(url: Any?) {
        imageUrl.value = url
    }

    private fun createPaletteAsync(bitmap: Bitmap, palette: (Palette) -> Unit) {
        Palette.from(bitmap).maximumColorCount(32).generate {
            if (it != null) palette(it)
        }
    }

    private fun getPaletteColors(palette: Palette): ArrayList<Int> {
        return ArrayList<Int>().apply {
            palette.lightVibrantSwatch?.rgb?.run rgb@{ add(this@rgb) }
            palette.vibrantSwatch?.rgb?.run rgb@{ add(this@rgb) }
            palette.darkVibrantSwatch?.rgb?.run rgb@{ add(this@rgb) }
            palette.lightMutedSwatch?.rgb?.run rgb@{ add(this@rgb) }
            palette.mutedSwatch?.rgb?.run rgb@{ add(this@rgb) }
            palette.darkMutedSwatch?.rgb?.run rgb@{ add(this@rgb) }
        }
    }
}