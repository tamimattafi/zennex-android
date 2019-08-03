package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpSimpleHolder
import com.tamimattafi.zennex.app.ui.custom.holders.Actions
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.item_view_holder_list_item.view.*

class ListItemHolder(itemView : View) : MvpSimpleHolder(itemView) ,
    ListContract.ListItemHolder {

    override var listPosition: Int = -1
    override var objectId: Int? = null

    override fun setChecked(isChecked: Boolean) {
        with(itemView) {
            checkbox.apply {
                setOnCheckedChangeListener(null)
                this.isChecked = isChecked
            }
            toggleImage(isChecked)
            setUpListener()
        }
    }

    private fun toggleImage(checked: Boolean) {
        itemView.image.setImageDrawable(
            AppUtils.getDrawable(
                itemView.context,
                if (checked) R.drawable.ic_on
                else R.drawable.ic_off
            )
        )
    }

    private fun setUpListener() {
        itemView.checkbox.apply {
            setOnCheckedChangeListener { _, checked ->
                toggleImage(checked)
                listener?.onHolderAction(listPosition, adapterPosition, Actions.ACTION_SWITCH, objectId)
            }
        }
    }

    override fun setDate(date: String) {
        itemView.date.text = date
    }

    override fun setName(name: String) {
        itemView.field.text = name
    }

    override fun getId(): Int? = objectId
}