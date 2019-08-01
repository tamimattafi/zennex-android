package com.tamimattafi.zennex.app.di.modules

import androidx.fragment.app.FragmentManager
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment

import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic @Provides
        fun provideChildFragmentManager(mainFragment: MainFragment) : FragmentManager
            = mainFragment.childFragmentManager

    }

}