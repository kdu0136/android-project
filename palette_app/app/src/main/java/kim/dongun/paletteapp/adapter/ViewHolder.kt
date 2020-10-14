package kim.dongun.paletteapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 어댑터 뷰 홀더에서 기본적으로 필요한 기능
 */
abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * 뷰 홀더 표현
     *
     * @param item 뷰 홀더에 보여져야 할 데이터
     */
    abstract fun display(item: Any)
}

