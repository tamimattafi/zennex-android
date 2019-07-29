package com.tamimattafi.zennex.app.ui.custom.holders.empty

import android.view.View
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.app.mvp.recycler.MvpSimpleHolder
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.item_view_holder_empty.view.*

class EmptyHolder(private val emptyHolderData: EmptyHolderData, itemView: View) : MvpSimpleHolder(itemView) ,
    MvpRecyclerContract.ListenerHolder {


    init {
        with(itemView) {
            emptyHolderData.apply {
                emptyLabel.text = label
                emptyDescription.text = description
                emptyImage.setImageDrawable(AppUtils.getDrawable(context, drawable))
                emptyButton.text = actionName
            }
        }
    }

}