package com.tamimattafi.zennex.app.di.modules

import androidx.fragment.app.FragmentManager
import com.tamimattafi.zennex.MainActivity
import com.tamimattafi.zennex.app.di.anotations.ActivityScoped
import com.tamimattafi.zennex.app.di.anotations.FragmentScoped
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.fragments.main.MainPagerAdapter
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ScalingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ParsingFragment

import com.tamimattafi.zennex.app.ui.global.NavigationContract
import com.tamimattafi.zennex.model.ApplicationDatabase
import com.tamimattafi.zennex.model.ListItemDao
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {



    @Module
    companion object {

        @JvmStatic @FragmentScoped @Provides
        fun provideListDao(applicationDatabase: ApplicationDatabase) : ListItemDao
            = applicationDatabase.listDao()

        @JvmStatic @FragmentScoped @Provides
        fun provideListFragment() : ListFragment = ListFragment()

        @JvmStatic @FragmentScoped @Provides
        fun provideScalingFragment() : ScalingFragment = ScalingFragment()

        @JvmStatic @FragmentScoped @Provides
        fun provideParsingFragment() : ParsingFragment = ParsingFragment()

        @JvmStatic @FragmentScoped @Provides
        fun provideMapFragment() : MapFragment = MapFragment()

        @JvmStatic @FragmentScoped @Provides
        fun provideChildFragmentManager(mainFragment: MainFragment) : FragmentManager
            = mainFragment.childFragmentManager

    }

    @ActivityScoped @Binds
    abstract fun bindNavigationManager(mainActivity: MainActivity) : NavigationContract.NavigationManager

}