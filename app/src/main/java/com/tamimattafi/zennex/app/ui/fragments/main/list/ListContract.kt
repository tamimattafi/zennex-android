package com.tamimattafi.zennex.app.ui.fragments.main.list

import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract

interface ListContract {

    interface Presenter : MvpRecyclerContract.Presenter<ListItemHolder>
    interface View : MvpRecyclerContract.View<ListItemHolder>

    interface ListItemHolder : MvpRecyclerContract.Holder {
        fun setName(name : String)
        fun setDate(date : String)
        fun setChecked(isChecked : Boolean)
    }
}