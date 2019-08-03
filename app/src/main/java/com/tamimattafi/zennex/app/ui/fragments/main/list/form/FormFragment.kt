package com.tamimattafi.zennex.app.ui.fragments.main.list.form

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.dialogs.specific.ConfirmationDialog
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_form_layout.*
import kotlinx.android.synthetic.main.toolbar_edit.*

abstract class FormFragment : NavigationContract.NavigationFragment(), FormContract.View {

    override val layoutId: Int = R.layout.fragment_form

    private var goBackConfirmed: Boolean = false

    abstract fun onFormSave(name: String)
    abstract fun getFromTitleId(): Int
    abstract fun canGoBack(): Boolean

    private val confirmationDialog by lazy {
        with(appActivity) {
            ConfirmationDialog(this).apply {
                title = resources.getString(R.string.cancel_action)
                hint = resources.getString(R.string.do_you_really_want_to_go_back)
                onConfirm = {
                    this@FormFragment.dismiss()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = appActivity.resources.getString(getFromTitleId())
        back.setOnClickListener { navigationManager.requestBackPress() }
        save.setOnClickListener {
            if (field.text.isNullOrEmpty()) {
                fieldLayout.error = appActivity.resources.getString(R.string.this_field_is_required)
            } else {
                fieldLayout.apply {
                    error = null
                    isErrorEnabled = false
                }

                onFormSave(field.text.toString().trim())
            }
        }

        discard.setOnClickListener { back.performClick() }
        confirm.setOnClickListener { save.performClick() }
    }

    override fun onBackPressed(): Boolean {
        return canGoBack() || goBackConfirmed.also { confirmed ->
            if (!confirmed) {
                confirmationDialog.show()
            }
        }
    }

    override fun showError(message: String) {
        AppUtils.showSnackBar(view!!, message)
    }

    override fun dismiss() {
        goBackConfirmed = true
        navigationManager.requestBackPress()
    }


}