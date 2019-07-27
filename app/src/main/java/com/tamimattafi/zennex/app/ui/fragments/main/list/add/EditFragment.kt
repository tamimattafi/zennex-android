package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.global.NavigationContract
import javax.inject.Inject

class EditFragment : NavigationContract.NavigationFragment(), EditContract.View {

    @Inject
    lateinit var presneter : EditContract.Presenter

    override var name: String = "fragment-edit"
    override val layoutId: Int = R.layout.fragment_edit


    override fun editItem(id: Int) {
        presneter.getItem(id)
    }

    override fun bindItemName(string: String) {

    }
}