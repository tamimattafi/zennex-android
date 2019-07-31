package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerAdapter
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolderList
import javax.inject.Inject

@ListScope
class ListAdapter @Inject constructor(presenter: MvpRecyclerContract.Presenter<ListContract.ListItemHolder>, listener : MvpRecyclerContract.Listener) : MvpRecyclerAdapter<ListContract.ListItemHolder>(presenter, listener) {

    override fun getItemHolder(parent: ViewGroup): RecyclerView.ViewHolder = ListItemHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_view_holder_list_item,
            parent,
            false
        )
    )

    override fun getNoDataHolderType(): Int = EmptyHolderList.EMPTY_LIST
}