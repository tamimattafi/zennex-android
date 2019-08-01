package com.tamimattafi.zennex.app.di.modules

import com.tamimattafi.zennex.app.di.modules.fragments.list.AddModule
import com.tamimattafi.zennex.app.di.modules.fragments.list.EditModule
import com.tamimattafi.zennex.app.di.modules.fragments.list.ListModule
import com.tamimattafi.zennex.app.di.modules.fragments.parsing.ParsingModule
import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.ui.fragments.main.MainFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.AddFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.edit.EditFragment
import com.tamimattafi.zennex.app.ui.fragments.main.map.MapFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ScalingFragment
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
    @ContributesAndroidInjector(modules = [MainModule::class, ListModule::class, AddModule::class])
    abstract fun provideAddFragment(): AddFragment

    @ListScope
    @ContributesAndroidInjector(modules = [MainModule::class, ListModule::class])
    abstract fun provideListFragment() : ListFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMapFragment() : MapFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideScalingFragment() : ScalingFragment

    @ParsingScope
    @ContributesAndroidInjector(modules = [MainModule::class, ParsingModule::class])
    abstract fun provideParsingFragment() : ParsingFragment

}