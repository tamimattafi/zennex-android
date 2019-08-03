package com.tamimattafi.zennex.app

import com.tamimattafi.zennex.app.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerApplicationComponent.builder().application(this).build()

}