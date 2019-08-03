package com.tamimattafi.zennex.app.ui.fragments.main.language

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ApplicationPreferences
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionDialogContract
import com.tamimattafi.zennex.app.ui.custom.dialogs.sub.StringSelectionDialog
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import kotlinx.android.synthetic.main.fragment_form_layout.*
import kotlinx.android.synthetic.main.toolbar_language.*
import javax.inject.Inject

class LanguageFragment : NavigationContract.NavigationFragment() {

    override var fragmentName: String = "fragment-language"
    override val layoutId: Int = R.layout.fragment_language

    @Inject
    lateinit var applicationPreferences: ApplicationPreferences

    lateinit var selectedLanguage: String

    private val languageDialog: StringSelectionDialog by lazy {
        StringSelectionDialog(activity).apply {
            bindData(
                LanguageValues.getLanguagesMenu(),
                object : SelectionDialogContract.ListDialogActionListener<String> {
                    override fun onItemSelected(item: String) {
                        selectedLanguage = item
                        field.setText(item)
                        dismiss()
                    }
                }
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedLanguage = applicationPreferences.getLanguage()
        with(field) {
            isFocusableInTouchMode = false
            setText(selectedLanguage)
            setOnClickListener {
                languageDialog.show()
            }
        }

        confirm.setOnClickListener {
            if (selectedLanguage != applicationPreferences.getLanguage()) {
                applicationPreferences.setLanguage(selectedLanguage)
                navigationManager.requestRestart()
            } else {
                navigationManager.requestBackPress()
            }
        }

        save.setOnClickListener { confirm.performClick() }

        discard.setOnClickListener {
            navigationManager.requestBackPress()
        }

        back.setOnClickListener { discard.performClick() }
    }
}