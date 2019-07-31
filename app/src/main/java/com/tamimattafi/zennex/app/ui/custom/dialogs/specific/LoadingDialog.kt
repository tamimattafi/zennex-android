package com.tamimattafi.zennex.app.ui.custom.dialogs.specific

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.tamimattafi.zennex.R
import kotlinx.android.synthetic.main.dialog_loading.view.*

class LoadingDialog(context: Context) {

    private val alertDialog: AlertDialog
    private val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null, false)

    var title: String = ""
        set(value) {
            field = value
            dialogView.loading_dialog_title.text = value
        }

    var hint: String = ""
        set(value) {
            field = value
            dialogView.loading_dialog_hint.text = value
        }

    init {
        AlertDialog.Builder(context).apply {
            setView(dialogView)
            setCancelable(false)
            alertDialog = create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    fun show() {
        alertDialog.show()
    }

    fun dismiss() {
        alertDialog.dismiss()
    }
}