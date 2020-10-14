package kim.dongun.paletteapp.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kim.dongun.paletteapp.dipToPxInt

/**
 * recycler view item off set 설정 class
 */
class CustomItemDecoration : RecyclerView.ItemDecoration {
    private var leftOffSet: Int = 0
    private var rightOffSet: Int = 0
    private var topOffSet: Int = 0
    private var bottomOffSet: Int = 0

    constructor(offset: Float = 0f) {
        val offSetDIP = offset.dipToPxInt
        this.leftOffSet = offSetDIP
        this.rightOffSet = offSetDIP
        this.topOffSet = offSetDIP
        this.bottomOffSet = offSetDIP
    }

    constructor(left: Float = 0f, right: Float = 0f, top: Float = 0f, bottom: Float = 0f) {
        this.leftOffSet = left.dipToPxInt
        this.rightOffSet = right.dipToPxInt
        this.topOffSet = top.dipToPxInt
        this.bottomOffSet = bottom.dipToPxInt
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = leftOffSet
            right = rightOffSet
            top = topOffSet
            bottom = bottomOffSet
        }
    }
}
