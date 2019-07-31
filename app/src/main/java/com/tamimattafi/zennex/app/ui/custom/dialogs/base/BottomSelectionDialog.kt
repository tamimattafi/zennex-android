package com.tamimattafi.zennex.app.ui.custom.dialogs.base

import android.content.Context

import android.view.LayoutInflater
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tamimattafi.zennex.R
import kotlinx.android.synthetic.main.dialog_bottom_selection.view.*

abstract class BottomSelectionDialog<OBJECT, HOLDER : SelectionDialogContract.SelectionDialogHolder<OBJECT>>(
    context: Context,
    spanSize: Int
) : BottomSheetDialog(context), SelectionDialogContract.SelectionDialog<OBJECT, HOLDER> {

    private val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_selection, null, false)

    init {
        this.setContentView(dialogView)
        with(dialogView.recycler) {
            layoutManager = GridLayoutManager(context, spanSize)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun bindData(data: ArrayList<OBJECT>, listener: SelectionDialogContract.ListDialogActionListener<OBJECT>) {
        dialogView.recycler.adapter = getSelectionAdapter(data, listener)
    }

}