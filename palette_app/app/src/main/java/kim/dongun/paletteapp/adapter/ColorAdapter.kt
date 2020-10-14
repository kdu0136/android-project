package kim.dongun.paletteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kim.dongun.paletteapp.R
import kim.dongun.paletteapp.databinding.ItemColorBinding
import java.util.*

class ColorAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val colorData: ArrayList<Int> = ArrayList()

    fun updateData(updateData: ArrayList<Int>) {
        this.colorData.clear()
        this.colorData.addAll(updateData)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        DataBindingUtil.inflate<ItemColorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_color,
            parent,
            false
        ).let {
            ItemColorViewHolder(binding = it)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.display(item = colorData[position])
    }

    override fun getItemCount(): Int = colorData.size
}