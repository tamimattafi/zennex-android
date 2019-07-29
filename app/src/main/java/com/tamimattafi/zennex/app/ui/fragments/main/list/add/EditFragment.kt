package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import android.os.Bundle
import android.util.Log
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.global.NavigationContract
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.toolbar_edit.*
import javax.inject.Inject

class EditFragment : NavigationContract.NavigationFragment(), EditContract.View {

    @Inject
    lateinit var presneter : EditContract.Presenter


    private var editItemId : Int? = null

    override var fragmentName: String = "fragment-edit"
    override val layoutId: Int = R.layout.fragment_edit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (editItemId != null) {
            Log.e("EditFragment", "trying edit $editItemId")
            presneter.getItem(id)
        }

        back.setOnClickListener { navigationManager.requestBackPress() }
        save.setOnClickListener {
            if (name.text.isNullOrEmpty()) {
                nameLayout.error = appContext.resources.getString(R.string.this_field_is_required)
            } else {
                nameLayout.apply {
                    error = null
                    isErrorEnabled = false
                }

                presneter.saveItem(name.text.toString())
            }
        }

        discard.setOnClickListener { back.performClick() }
        confirm.setOnClickListener { save.performClick() }
    }


    override fun editItem(id: Int) {
        Log.e("EditFragment", "request edit $id")
        this.editItemId = id
    }

    override fun dismiss() {
        navigationManager.requestBackPress()
    }

    override fun bindItemName(string: String) {
        name.setText(string)
    }

    override fun onBackPressed(): Boolean {
       return true
    }

}