package com.tamimattafi.zennex

import android.os.Bundle
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.global.NavigationActivity
import javax.inject.Inject

class MainActivity : NavigationActivity() {

    override val layoutId: Int = R.layout.activity_main
    override var rootId: Int = R.id.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        requestAttachBaseScreen(MainFragment())
    }

    override fun applyNewStyle() {
        theme.applyStyle(R.style.AppTheme, true)
    }
}
