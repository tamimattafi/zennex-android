package com.tamimattafi.zennex.app.ui.global

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tamimattafi.zennex.R
import dagger.android.support.DaggerAppCompatActivity


abstract class NavigationActivity : DaggerAppCompatActivity(), NavigationContract.NavigationManager {

    abstract val layoutId : Int
    abstract var rootId : Int


    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    private val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(rootId)

    private var baseFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyNewStyle()
        setContentView(layoutId)
        if (savedInstanceState == null) {
            onActivityCreated(savedInstanceState)
            supportFragmentManager.addOnBackStackChangedListener {
                (currentFragment as? NavigationContract.SelectionListener)?.onSelected()
            }
        }
    }

    open fun applyNewStyle() {}

    override fun requestAttachBaseScreen(fragment: NavigationContract.NavigationFragment) {
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.inTransaction{
            replace(rootId, fragment)
        }
        baseFragment = fragment
    }

    override fun requestSlideLeftScreen(fragment: NavigationContract.NavigationFragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
            add(rootId, fragment).addToBackStack(fragment.fragmentName)
        }
    }
    override fun requestSlideRightScreen(fragment: NavigationContract.NavigationFragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit)
            add(rootId, fragment).addToBackStack(fragment.fragmentName)
        }
    }

    override fun requestFadeInScreen(fragment: NavigationContract.NavigationFragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
            add(rootId, fragment).addToBackStack(fragment.fragmentName)
        }
    }

    override fun requestAttachScreen(fragment: NavigationContract.NavigationFragment) {
        supportFragmentManager.inTransaction {
            add(rootId, fragment).addToBackStack(fragment.fragmentName)
        }
    }

    override fun onBackPressed() {
        (currentFragment as? NavigationContract.BackPressController)?.let {
            if (it.onBackPressed()) {
                super.onBackPressed()
            }
        } ?: super.onBackPressed()
    }

    override fun requestBackPress() {
        onBackPressed()
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    override fun startActivityForResult(intent: Intent) {
        startActivityForResult(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        (currentFragment as? NavigationContract.ActivityResultReceiver)?.onActivityResult(requestCode, resultCode, data)
    }
}