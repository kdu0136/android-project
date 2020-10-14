package kim.dongun.paletteapp

import android.content.res.Resources
import android.util.TypedValue

// dip 값으로 변환
val Float.pxToDip: Float get() = this / Resources.getSystem().displayMetrics.density
val Float.pxToDipInt: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Float.pxToSp: Float get() = this / Resources.getSystem().displayMetrics.scaledDensity

val Float.dipToPx: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
val Float.dipToPxInt: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()
val Float.spToPx: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
