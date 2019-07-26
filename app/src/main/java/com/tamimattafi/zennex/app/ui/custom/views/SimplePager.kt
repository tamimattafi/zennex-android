package com.tamimattafi.zennex.app.ui.custom.views

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class SimplePager(context: Context, attributeSet: AttributeSet) : ViewPager(context, attributeSet) {

    fun setPageSelectionListener(func : (position : Int) -> Unit ) {
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                func(position)
            }

        })
    }

}