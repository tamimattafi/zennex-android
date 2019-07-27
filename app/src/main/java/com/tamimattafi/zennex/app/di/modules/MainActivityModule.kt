package com.tamimattafi.zennex.app.di.modules

import com.tamimattafi.zennex.MainActivity
import com.tamimattafi.zennex.app.di.anotations.ActivityScope
import com.tamimattafi.zennex.app.di.modules.fragments.list.ListModule
import com.tamimattafi.zennex.app.di.modules.fragments.list.edit.EditModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentModule::class])
    abstract fun mainActivity() : MainActivity

}