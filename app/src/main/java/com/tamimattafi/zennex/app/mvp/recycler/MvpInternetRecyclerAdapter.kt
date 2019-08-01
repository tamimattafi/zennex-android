package com.tamimattafi.zennex.app.mvp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolder
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolderList
import com.tamimattafi.zennex.utils.PhoneUtils

abstract class MvpInternetRecyclerAdapter<HOLDER : MvpRecyclerContract.Holder>(
    override val presenter: MvpRecyclerContract.InternetPresenter<HOLDER>,
    listener: MvpRecyclerContract.Listener
) : MvpLocalRecyclerAdapter<HOLDER>(presenter, listener), MvpRecyclerContract.InternetRecyclerAdapter<HOLDER> {

    override var networkError: Boolean = false

    override fun refresh() {
        dataCount = 0
        prepare()
        presenter.refresh(this)
    }

    override fun tryAgain() {
        prepare()
        presenter.loadMoreRecyclerData(this)
    }

    private fun prepare() {
        isLoading = false
        allData = false
        networkError = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        with(LayoutInflater.from(parent.context)) {
            return when {
                viewType == ITEM_NO_DATA && (!PhoneUtils.isConnected(parent.context) || networkError) -> getNoDataHolder(
                    parent,
                    EmptyHolderList.NO_CONNECTION
                )
                viewType == ITEM_LOADING_ERROR -> EmptyHolder(
                    EmptyHolderList.getItem(parent.context, EmptyHolderList.TRY_AGAIN),
                    inflate(
                        R.layout.item_view_holder_bottom_error,
                        parent,
                        false
                    )
                )
                else -> super.onCreateViewHolder(parent, viewType)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataCount > 0 && position == dataCount + headersCount + footersCount && networkError) {
            ITEM_LOADING_ERROR
        } else super.getItemViewType(position)
    }

}