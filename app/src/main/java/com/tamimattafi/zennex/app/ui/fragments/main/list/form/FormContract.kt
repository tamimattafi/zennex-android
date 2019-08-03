package com.tamimattafi.zennex.app.ui.fragments.main.list.form

import com.tamimattafi.zennex.app.mvp.BaseContract
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract

interface FormContract {

    interface Presenter : BaseContract.Presenter {
        fun saveItem(name: String)
    }


    interface View : NavigationContract.BackPressController {
        fun dismiss()
        fun showError(message: String)
    }
}