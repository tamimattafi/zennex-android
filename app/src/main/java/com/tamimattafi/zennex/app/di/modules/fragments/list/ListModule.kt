package com.tamimattafi.zennex.app.di.modules.fragments.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListAdapter
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListPresenter
import com.tamimattafi.zennex.model.ApplicationDatabase
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.repository.list.ListRepository
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
    abstract fun bindListAdapterPreseneter(listPresenter : ListPresenter) : MvpRecyclerContract.Presenter<ListContract.ListItemHolder>

    @ListScope @Binds
    abstract fun bindListView(listFragment: ListFragment) : ListContract.View

    @ListScope @Binds
    abstract fun bindListRepository(listRepository: ListRepository) : RepositoryContract.Base<ListItem>

    @ListScope @Binds
    abstract fun bindListAdapter(listAdapter: ListAdapter) : MvpRecyclerContract.RecyclerAdapter<ListContract.ListItemHolder>

    @ListScope @Binds
    abstract fun bindListListener(listFragment: ListFragment) : MvpRecyclerContract.Listener
}