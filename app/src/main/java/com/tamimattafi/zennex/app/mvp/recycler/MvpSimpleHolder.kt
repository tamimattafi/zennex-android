package com.tamimattafi.zennex.app.mvp.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MvpSimpleHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
    MvpRecyclerContract.ListenerHolder {

    override var listPosition: Int = -1

    override var listener: MvpRecyclerContract.Listener? = null
        set(value) {
            field = value
            itemView.setOnClickListener {
                value?.onHolderClick(listPosition, adapterPosition, getId())
            }
        }

    open fun getId(): Int? = null



}