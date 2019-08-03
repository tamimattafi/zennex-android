package com.tamimattafi.zennex.app.ui.fragments.global

import android.content.Intent
import com.tamimattafi.zennex.utils.KeyboardUtils
import javax.inject.Inject


interface NavigationContract {

    interface NavigationManager {
        fun requestAttachBaseScreen(fragment: NavigationFragment)
        fun requestSlideLeftScreen(fragment: NavigationFragment)
        fun requestSlideRightScreen(fragment: NavigationFragment)
        fun requestFadeInScreen(fragment: NavigationFragment)
        fun requestAttachScreen(fragment: NavigationFragment)
        fun requestBackPress()
        fun requestActivityForResult(resultReceiver: ActivityResultReceiver, intent: Intent, requestCode: Int)
        fun requestRestart()
    }

    abstract class NavigationFragment : BaseFragment() {

        @Inject
        lateinit var navigationManager: NavigationManager

        abstract var fragmentName: String

        override fun onDestroyView() {
            super.onDestroyView()
            KeyboardUtils.hide(context!!)
        }
    }

    interface BackPressController {
        fun onBackPressed(): Boolean
    }

    interface SelectionListener {
        fun onSelected()
    }

    interface ActivityResultReceiver {
        fun onReceiveActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}