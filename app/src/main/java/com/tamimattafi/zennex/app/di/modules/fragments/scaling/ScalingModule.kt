package com.tamimattafi.zennex.app.di.modules.fragments.scaling

import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.ScalingFragment
import dagger.Binds
import dagger.Module

@Module
abstract class ScalingModule {


    @Binds
    abstract fun bindResultReceiver(scalingFragment: ScalingFragment): NavigationContract.ActivityResultReceiver

}