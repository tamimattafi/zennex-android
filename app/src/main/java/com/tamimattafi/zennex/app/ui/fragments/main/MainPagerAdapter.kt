package com.tamimattafi.zennex.app.ui.fragments.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ScalingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ParsingFragment
import com.tamimattafi.zennex.app.ui.global.NavigationContract
import javax.inject.Inject

class MainPagerAdapter @Inject constructor(fragmentManager: FragmentManager,
                                           listFragment: ListFragment,
                                           scalingFragment: ScalingFragment,
                                           parsingFragment: ParsingFragment,
                                           mapFragment: MapFragment)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments: List<NavigationContract.NavigationFragment>
            = arrayListOf(
                listFragment,
                scalingFragment,
                parsingFragment,
                mapFragment
            )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}