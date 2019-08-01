package com.tamimattafi.zennex.app.di.modules.fragments.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListAdapter
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListPresenter
import com.tamimattafi.zennex.model.list.ListItemDao
import com.tamimattafi.zennex.repository.ApplicationDatabase
import com.tamimattafi.zennex.repository.list.ListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ListModule {


    @Module
    companion object {
        @JvmStatic
        @ListScope
        @Provides
        fun provideListDao(applicationDatabase: ApplicationDatabase): ListItemDao = applicationDatabase.listDao()
    }

    @ListScope @Binds
    abstract fun bindListPreseneter(listPresenter : ListPresenter) : ListContract.Presenter

    @ListScope @Binds
    abstract fun bindListView(listFragment: ListFragment) : ListContract.View

    @ListScope @Binds
    abstract fun bindListAdapter(listAdapter: ListAdapter): ListContract.Adapter

    @ListScope @Binds
    abstract fun bindListRepository(listRepository: ListRepository): ListContract.Repository

}