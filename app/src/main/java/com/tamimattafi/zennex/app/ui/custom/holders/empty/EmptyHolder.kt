package com.tamimattafi.zennex.app.ui.custom.holders.empty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.item_view_holder_empty.view.*

class EmptyHolder(private val emptyHolderData: EmptyHolderData, itemView: View) : RecyclerView.ViewHolder(itemView),
    MvpRecyclerContract.ListenerHolder {

    override var listPosition: Int = -1

    override var listener: MvpRecyclerContract.Listener? = null
        set(value) {
            field = value
            itemView.action.setOnClickListener {
                value?.onHolderAction(listPosition, adapterPosition, emptyHolderData.action, null)
            }
        }

    init {
        with(itemView) {
            emptyHolderData.apply {
                this@with.label.text = label
                this@with.description.text = description
                this@with.image.setImageDrawable(AppUtils.getDrawable(context, image))
                this@with.action.text = actionName
            }
        }
    }

}