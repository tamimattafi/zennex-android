package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.content.Context
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.dialogs.model.MenuItemData

object ListValues {


    const val EDIT_ITEM = 0
    const val DELETE_ITEM = 1


    fun getContextMenu(context: Context): ArrayList<MenuItemData> = ArrayList<MenuItemData>().apply {
        add(
            MenuItemData(
                id = EDIT_ITEM,
                label = context.resources.getString(R.string.edit),
                drawable = R.drawable.ic_edit
            )
        )

        add(
            MenuItemData(
                id = DELETE_ITEM,
                label = context.resources.getString(R.string.delete),
                drawable = R.drawable.ic_delete
            )
        )
    }
}