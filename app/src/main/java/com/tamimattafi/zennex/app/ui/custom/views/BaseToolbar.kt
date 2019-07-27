package com.tamimattafi.zennex.app.ui.custom.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

class BaseToolbar(context: Context, attributeSet: AttributeSet) : Toolbar(context, attributeSet) {
    init {
        contentInsetEndWithActions = 0
        contentInsetStartWithNavigation = 0
        setContentInsetsAbsolute(0, 0)
        navigationIcon = null
        title = null
    }
}