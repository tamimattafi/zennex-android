package com.tamimattafi.zennex.app.di.modules

import androidx.fragment.app.FragmentManager
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ScalingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ParsingFragment

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic @Provides
        fun provideChildFragmentManager(mainFragment: MainFragment) : FragmentManager
            = mainFragment.childFragmentManager

    }

}