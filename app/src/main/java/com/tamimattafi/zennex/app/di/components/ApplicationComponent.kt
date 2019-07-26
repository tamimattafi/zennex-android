package com.tamimattafi.zennex.app.di.components

import com.tamimattafi.zennex.app.di.modules.ActivityBindingModule
import com.tamimattafi.zennex.app.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication) : Builder

        fun build() : ApplicationComponent
    }

}