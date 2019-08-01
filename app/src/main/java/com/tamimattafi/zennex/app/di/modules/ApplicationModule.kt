package com.tamimattafi.zennex.app.di.modules

import android.content.Context
import androidx.room.Room
import com.tamimattafi.zennex.repository.ApplicationDatabase
import com.tamimattafi.zennex.repository.InternetService
import com.tamimattafi.zennex.repository.RepositoryValues
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Module
    companion object {
        @JvmStatic @Singleton @Provides
        fun provideApplicationDatabase(context: Context) : ApplicationDatabase =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, RepositoryValues.DATABASE_NAME)
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RepositoryValues.SERVICE_BASE_URL)
            .build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideInternetService(retrofit: Retrofit): InternetService = retrofit.create(InternetService::class.java)
    }

    @Singleton
    @Binds
    abstract fun bindContext(application : DaggerApplication) : Context


}