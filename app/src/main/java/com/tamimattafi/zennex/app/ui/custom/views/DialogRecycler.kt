package com.tamimattafi.zennex.app.ui.custom.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.utils.AppUtils

class DialogRecycler(context: Context, attributeSet: AttributeSet) : RecyclerView(context, attributeSet) {
    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(
            widthSpec,
            MeasureSpec.makeMeasureSpec(AppUtils.convertDpToPixel(300F, context).toInt(), MeasureSpec.AT_MOST)
        )
    }

}