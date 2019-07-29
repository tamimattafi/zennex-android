package com.tamimattafi.zennex.app.di.modules

import com.tamimattafi.zennex.app.di.modules.fragments.list.ListModule
import com.tamimattafi.zennex.app.di.modules.fragments.list.edit.EditModule
import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ScalingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ParsingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainFragment() : MainFragment

    @ListScope
    @ContributesAndroidInjector(modules = [MainModule::class, ListModule::class, EditModule::class])
    abstract fun provideEditFragment() : EditFragment

    @ListScope
    @ContributesAndroidInjector(modules = [MainModule::class, ListModule::class])
    abstract fun provideListFragment() : ListFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMapFragment() : MapFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideScalingFragment() : ScalingFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideParsingFragment() : ParsingFragment

}