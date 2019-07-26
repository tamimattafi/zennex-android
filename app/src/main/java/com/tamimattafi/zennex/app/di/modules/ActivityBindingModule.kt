package com.tamimattafi.zennex.app.di.modules

import com.tamimattafi.zennex.MainActivity
import com.tamimattafi.zennex.app.di.anotations.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class, FragmentBindingModule::class])
    abstract fun mainActivity() : MainActivity

}