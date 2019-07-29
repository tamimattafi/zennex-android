package com.tamimattafi.zennex.app.mvp.recycler

import androidx.recyclerview.widget.RecyclerView


interface MvpRecyclerContract {

    interface RecyclerAdapter<HOLDER : Holder> : PagerRecycler,
        TryAgain {
        var allData : Boolean
        var isLoading : Boolean
        var controller: RecyclerController<HOLDER>?
        fun getViewHolder(listPosition : Int) : HOLDER?
        fun setDataCount(dataCount : Int) : Boolean
        fun refresh()
    }

    interface RecyclerController<HOLDER : Holder> {
        val recycler : RecyclerView
        fun prepare(adapter : RecyclerAdapter<HOLDER>) : Boolean
        fun getViewHolder(position : Int) : HOLDER?
    }

    interface PagerRecycler {
        fun loadMore()
    }

    interface TryAgain {
        fun tryAgain()
    }

    interface Listener {
        fun onHolderClick(listPosition: Int, adapterPosition : Int, itemId : Int?)
        fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?)
        fun onHolderAction(adapterPosition: Int, action : Int, itemId: Int?)
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

    interface Presenter<HOLDER : Holder> {
        fun bindViewHolder(holder: HOLDER)
        fun loadMoreRecyclerData(recycler : RecyclerAdapter<HOLDER>)
        fun refresh(recycler: RecyclerAdapter<HOLDER>)
    }

    interface View<HOLDER : Holder> : Listener{
        fun showError(message : String)
    }
}