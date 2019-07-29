package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpSimpleHolder
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.item_view_holder_list_item.view.*

class ListItemHolder(itemView : View) : MvpSimpleHolder(itemView) ,
    ListContract.ListItemHolder {

    override var listPosition: Int = -1
    override var objectId: Int? = null

    override fun setChecked(isChecked: Boolean) {
        with(itemView) {
            checkbox.apply {
                setOnCheckedChangeListener { _, checked ->
                    this@with.image.setImageDrawable(
                        AppUtils.getDrawable(context,
                            if (checked) R.drawable.ic_on
                            else R.drawable.ic_off
                        )
                    )
                }
            }.isChecked = isChecked
        }
    }

    override fun setDate(date: String) {

    }

    override fun setName(name: String) {
        itemView.name.text = name
    }

    override fun getId(): Int? = objectId
}