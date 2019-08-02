package com.tamimattafi.zennex.app.ui.fragments.main

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : NavigationContract.NavigationFragment() {

    override var fragmentName: String = "fragment-main"
    override val layoutId: Int = R.layout.fragment_main

    @Inject
    lateinit var adapter : MainPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setUpViewPager(adapter)
            setUpNavigation()
        }
    }

    private fun setUpViewPager(adapter: MainPagerAdapter) {
        pager.apply {

            setPageSelectionListener { position ->
                navigation.selectedItemId = when (position) {
                    0 -> R.id.nav_list
                    1 -> R.id.nav_scaling
                    2 -> R.id.nav_parsing
                    3 -> R.id.nav_map
                    else -> throw NullPointerException("Item not found for ViewPager page with the position: $position")
                }
            }

            this.adapter = adapter
        }
    }

    private fun setUpNavigation() {
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            pager.currentItem = when (menuItem.itemId) {
                R.id.nav_list -> 0
                R.id.nav_scaling -> 1
                R.id.nav_parsing -> 2
                R.id.nav_map -> 3
                else -> throw NullPointerException("Page not found for BottomNavigation item with the id: ${menuItem.itemId}")
            }
            true
        }
    }

}