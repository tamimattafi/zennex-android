package com.tamimattafi.zennex.app.mvp.recycler

import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.app.mvp.BaseContract


interface MvpRecyclerContract {

    interface RecyclerAdapter<HOLDER : Holder> : PagerRecycler {
        var allData : Boolean
        var isLoading : Boolean
        var controller: RecyclerController<HOLDER>?
        fun getViewHolder(listPosition : Int) : HOLDER?
        fun setDataCount(dataCount : Int) : Boolean
        fun isEmpty(): Boolean
    }

    interface InternetRecyclerAdapter<HOLDER : Holder> : RecyclerAdapter<HOLDER>, Refresher {
        var networkError: Boolean
    }

    interface RecyclerController<HOLDER : Holder> {
        val recycler : RecyclerView
        fun prepare(adapter : RecyclerAdapter<HOLDER>) : Boolean
        fun getViewHolder(position : Int) : HOLDER?
    }

    interface Refresher {
        fun refresh()
        fun tryAgain()
    }

    interface PagerRecycler {
        fun loadMore()
    }

    interface Listener {
        fun onHolderClick(listPosition: Int, adapterPosition : Int, itemId : Int?)
        fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?)
        fun onHolderAction(listPosition: Int, adapterPosition: Int, action: Int, itemId: Int?)
    }

    interface ListenerHolder {
        var listPosition : Int
        var listener : Listener?
    }

    interface Holder : ListenerHolder {
        var objectId : Int?
    }

    interface Object<out ID> {
        fun getObjectId() : ID
    }

    interface Presenter<HOLDER : Holder> : BaseContract.Presenter {
        fun bindViewHolder(holder: HOLDER)
        fun loadMoreRecyclerData(recycler : RecyclerAdapter<HOLDER>)
    }

    interface InternetPresenter<HOLDER : Holder> : Presenter<HOLDER> {
        fun refresh(recycler: RecyclerAdapter<HOLDER>)
    }

    interface View<HOLDER : Holder> : Listener{
        fun showError(message : String)
    }

    interface RefreshableView<HOLDER : Holder> : View<HOLDER> {
        fun setRefreshing(refreshing: Boolean)
    }
}