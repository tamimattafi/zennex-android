package com.tamimattafi.zennex.app.di.modules.fragments.list

import com.tamimattafi.zennex.app.di.anotations.FragmentScope
import com.tamimattafi.zennex.model.ApplicationDatabase
import com.tamimattafi.zennex.model.ListItemDao
import dagger.Module
import dagger.Provides

@Module
abstract class ListModule {

    @Module
    companion object {
        @JvmStatic
        @FragmentScope
        @Provides
        fun provideListDao(applicationDatabase: ApplicationDatabase): ListItemDao = applicationDatabase.listDao()
    }
}