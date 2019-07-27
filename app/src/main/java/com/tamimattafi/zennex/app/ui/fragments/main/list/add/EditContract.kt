package com.tamimattafi.zennex.app.ui.fragments.main.list.add

interface EditContract {

    interface Presenter {
        fun getItem(id: Int)
        fun isNameEdited(name : String) : Boolean
        fun saveNewItem()
    }

    interface View {
        fun editItem(id : Int)
        fun bindItemName(string: String)
    }
}