package com.tamimattafi.zennex.app.ui.custom.holders.dialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionDialogContract
import kotlinx.android.synthetic.main.item_view_holder_simple_string.view.*

class SimpleStringItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    SelectionDialogContract.SelectionDialogHolder<String> {

    lateinit var item: String

    override fun bindData(item: String) {
        this.item = item
        itemView.text.text = item
    }

    override fun bindListeners(listener: SelectionDialogContract.ListDialogActionListener<String>) {
        listener.onItemSelected(item)
    }


}