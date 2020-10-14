package kim.dongun.paletteapp.adapter

import kim.dongun.paletteapp.PrintLog
import kim.dongun.paletteapp.databinding.ItemColorBinding

class ItemColorViewHolder(private val binding: ItemColorBinding) : ViewHolder(binding.root) {
    override fun display(item: Any) {
        if (item is Int) {
            binding.apply {
                color = item
                root.setOnClickListener {
                    PrintLog.d(
                        "touch color",
                        String.format("%06x", (0xFFFFFF and color)).toUpperCase()
                    )
                }
            }
        }
    }
}