package com.tamimattafi.zennex.app

import android.content.Context
import android.os.Bundle
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationActivity
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment

class MainActivity : NavigationActivity() {

    override val layoutId: Int = R.layout.activity_main
    override var rootId: Int = R.id.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        requestAttachBaseScreen(MainFragment())
    }

    override fun applyNewStyle() {
        theme.applyStyle(R.style.AppTheme, true)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            ContextWrapper.wrap(
                newBase,
                ApplicationPreferences(newBase).getLanguage()
            )
        )
    }
}
