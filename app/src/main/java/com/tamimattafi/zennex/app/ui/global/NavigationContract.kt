package com.tamimattafi.zennex.app.ui.global

import android.content.Intent
import javax.inject.Inject


interface NavigationContract {

    interface NavigationManager {
        fun requestAttachBaseScreen(fragment: NavigationFragment)
        fun requestSlideLeftScreen(fragment: NavigationFragment)
        fun requestSlideRightScreen(fragment: NavigationFragment)
        fun requestFadeInScreen(fragment: NavigationFragment)
        fun requestAttachScreen(fragment: NavigationFragment)
        fun requestBackPress()
        fun startActivityForResult(intent : Intent)
    }

    abstract class NavigationFragment : BaseFragment() {

        @Inject
        lateinit var navigationManager : NavigationManager

        abstract var name : String
    }

    interface BackPressController {
        fun onBackPressed() : Boolean
    }

    interface SelectionListener {
        fun onSelected()
    }

    interface ActivityResultReceiver {
        fun onActivityResult(requestCode : Int, resultCode : Int, data: Intent?)
    }


}