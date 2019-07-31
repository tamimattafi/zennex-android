package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormContract

interface AddContract {

    interface Presenter : FormContract.Presenter
    interface View : FormContract.View
}