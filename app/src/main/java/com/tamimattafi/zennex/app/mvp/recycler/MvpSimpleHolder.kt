package com.tamimattafi.zennex.app.mvp.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MvpSimpleHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
    MvpRecyclerContract.ListenerHolder {

    override var listPosition: Int = -1

    override var listener: MvpRecyclerContract.Listener? = null
        set(value) {
            field = value
            onListenerSet(value)
        }

    open fun onListenerSet(listener: MvpRecyclerContract.Listener?) {
        with(itemView) {
            setOnClickListener {
                listener?.onHolderClick(listPosition, adapterPosition, this@MvpSimpleHolder.getId())
            }

            setOnLongClickListener {
                listener?.onHolderLongClick(listPosition, adapterPosition, this@MvpSimpleHolder.getId())
                true
            }
        }
    }



    open fun getId(): Int? = null



}