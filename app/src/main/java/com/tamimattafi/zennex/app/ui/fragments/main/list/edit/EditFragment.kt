package com.tamimattafi.zennex.app.ui.fragments.main.list.edit


import android.content.Context
import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormFragment
import kotlinx.android.synthetic.main.fragment_form.*
import javax.inject.Inject

class EditFragment(override val itemName: String, override val itemId: Int) : FormFragment(), EditContract.View {

    @Inject
    lateinit var presenter: EditContract.Presenter

    override var fragmentName: String = "fragment-edit"

    override fun onFormSave(name: String) {
        presenter.saveItem(name)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.setItemId(itemId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name.setText(itemName)
    }

    override fun getFromTitleId(): Int = R.string.edit_item

    override fun canGoBack(): Boolean = name.text.toString() == itemName

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}