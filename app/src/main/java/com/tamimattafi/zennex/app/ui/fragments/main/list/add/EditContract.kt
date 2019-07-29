package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import com.tamimattafi.zennex.app.ui.global.NavigationContract

interface EditContract {

    interface Presenter {
        fun getItem(id: Int)
        fun isNameEdited(name : String) : Boolean
        fun saveItem(name : String)
    }

    interface View : NavigationContract.BackPressController {
        fun editItem(id : Int)
        fun bindItemName(string: String)
        fun dismiss()
    }
}