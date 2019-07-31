package com.tamimattafi.zennex.app.ui.custom.dialogs.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.tamimattafi.zennex.R
import kotlinx.android.synthetic.main.dialog_selection.view.*


abstract class SelectionDialog<OBJECT, HOLDER : SelectionDialogContract.SelectionDialogHolder<OBJECT>>(
    context: Context?,
    spanSize: Int
) : SelectionDialogContract.SelectionDialog<OBJECT, HOLDER> {

    private val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_selection, null, false)
    private val dialog: AlertDialog
    private var cancelListener: SelectionDialogCancelListener? = null

    init {
        AlertDialog.Builder(context).apply {
            setView(dialogView)
            setCancelable(true)
            dialog = create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialogView.apply {
            cancel.setOnClickListener {
                dismiss()
                cancelListener?.onCancel()
            }

            with(recycler) {
                layoutManager = GridLayoutManager(context, spanSize).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(p0: Int): Int {
                            return if (itemCount <= 1) spanSize
                            else 1
                        }
                    }
                }
                itemAnimator = DefaultItemAnimator()
            }

        }

    }

    override fun bindData(data: ArrayList<OBJECT>, listener: SelectionDialogContract.ListDialogActionListener<OBJECT>) {
        dialogView.recycler.adapter = getSelectionAdapter(data, listener)
    }

    fun setOnCancelListener(cancelListener: SelectionDialogCancelListener) {
        this.cancelListener = cancelListener
    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

    interface SelectionDialogCancelListener {
        fun onCancel()
    }

}