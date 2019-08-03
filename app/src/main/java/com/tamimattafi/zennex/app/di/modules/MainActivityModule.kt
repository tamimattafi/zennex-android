package com.tamimattafi.zennex.app.di.modules

import android.app.Activity
import com.tamimattafi.zennex.app.MainActivity
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun mainActivity() : MainActivity

    @Binds
    abstract fun bindNavigationManager(mainActivity: MainActivity) : NavigationContract.NavigationManager

    @Binds
    abstract fun bindActivity(mainActivity: MainActivity): Activity

}