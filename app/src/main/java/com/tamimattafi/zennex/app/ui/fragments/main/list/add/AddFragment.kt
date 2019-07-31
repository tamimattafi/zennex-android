package com.tamimattafi.zennex.app.ui.fragments.main.list.add


import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormFragment
import kotlinx.android.synthetic.main.fragment_form.*
import javax.inject.Inject

class AddFragment : FormFragment(), AddContract.View {

    @Inject
    lateinit var presenter: AddContract.Presenter

    override var fragmentName: String = "fragment-add"

    override fun onFormSave(name: String) {
        presenter.saveItem(name)
    }

    override fun getFromTitleId(): Int = R.string.add_item

    override fun canGoBack(): Boolean = name.text.isNullOrEmpty()

}