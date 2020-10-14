package kim.dongun.paletteapp

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import kim.dongun.paletteapp.adapter.ColorAdapter

@BindingAdapter("bind:background")
fun setBackgroundColor(view: View, color: Int) {
    view.setBackgroundColor(color)
}

@BindingAdapter("bind:items")
fun setItems(view: RecyclerView, items: ArrayList<Int>?) {
    view.adapter = ColorAdapter().apply {
        updateData(updateData = items ?: ArrayList())
        notifyDataSetChanged()
    }
}

@BindingAdapter(value = ["bind:setImage", "bind:imageListener"], requireAll = false)
fun setImage(view: ImageView, data: Any?, listener: RequestListener<Drawable>? = null) {
    if (data != null)
        Glide.with(view.context)
            .load(data)
            .listener(listener)
            .into(view)
}

@BindingAdapter("bind:onTouchListener")
fun setOnTouchListener(view: View, onTouchListener: View.OnTouchListener?) {
    view.setOnTouchListener(onTouchListener)
}
