package com.tamimattafi.zennex.app.ui.custom.dialogs.sub

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.BottomSelectionDialog
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionAdapter
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionDialogContract
import com.tamimattafi.zennex.app.ui.custom.dialogs.model.MenuItemData
import com.tamimattafi.zennex.app.ui.custom.holders.dialog.MenuItemHolder

open class MenuBottomSelectionDialog(context: Context) :
    BottomSelectionDialog<MenuItemData, MenuItemHolder>(context, 1) {

    override fun getSelectionAdapter(
        data: ArrayList<MenuItemData>,
        listener: SelectionDialogContract.ListDialogActionListener<MenuItemData>
    ): SelectionAdapter<MenuItemData, MenuItemHolder> = MenuBottomSelectionAdapter(data, listener)

    inner class MenuBottomSelectionAdapter(
        data: ArrayList<MenuItemData>,
        listener: SelectionDialogContract.ListDialogActionListener<MenuItemData>
    ) : SelectionAdapter<MenuItemData, MenuItemHolder>(data, listener) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MenuItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_holder_menu_item,
                parent,
                false
            )
        )

    }

}