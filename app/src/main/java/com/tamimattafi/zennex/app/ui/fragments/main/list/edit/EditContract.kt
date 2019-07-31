package com.tamimattafi.zennex.app.ui.fragments.main.list.edit

import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormContract

interface EditContract {

    interface Presenter : FormContract.Presenter {
        fun setItemId(id: Int)
    }

    interface View : FormContract.View {
        val itemName: String
        val itemId: Int
    }
}