package com.tamimattafi.zennex.app.ui.custom.holders.dialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionDialogContract
import com.tamimattafi.zennex.app.ui.custom.dialogs.model.MenuItemData
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.item_view_holder_menu_item.view.*

class MenuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    SelectionDialogContract.SelectionDialogHolder<MenuItemData> {

    lateinit var item: MenuItemData

    override fun bindData(item: MenuItemData) {
        this.item = item
        with(itemView) {
            item.apply {
                this@with.label.text = label
                with(this@with.icon) {
                    setColorFilter(AppUtils.getColor(context, drawableColor))
                    setImageResource(this@apply.drawable ?: return)
                }
            }
        }
    }

    override fun bindListeners(listener: SelectionDialogContract.ListDialogActionListener<MenuItemData>) {
        itemView.setOnClickListener {
            listener.onItemSelected(item)
        }
    }

}