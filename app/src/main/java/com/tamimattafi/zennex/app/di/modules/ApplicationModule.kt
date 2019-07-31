package com.tamimattafi.zennex.app.di.modules

import android.content.Context
import androidx.room.Room
import com.tamimattafi.zennex.model.ApplicationDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Module
    companion object {
        @JvmStatic @Singleton @Provides
        fun provideApplicationDatabase(context: Context) : ApplicationDatabase
            = Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.NAME)
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Singleton
    @Binds
    abstract fun bindContext(application : DaggerApplication) : Context
}