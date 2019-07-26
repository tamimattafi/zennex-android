package com.tamimattafi.zennex.app.di.modules

import com.tamimattafi.zennex.app.di.anotations.FragmentScoped
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ScalingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ParsingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainFragment() : MainFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideListFragment() : ListFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMapFragment() : MapFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideScalingFragment() : ScalingFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideParsingFragment() : ParsingFragment

}