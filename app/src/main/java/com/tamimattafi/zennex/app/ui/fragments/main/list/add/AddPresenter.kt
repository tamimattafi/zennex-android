package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import com.tamimattafi.zennex.app.ui.fragments.main.list.ListContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormPresenter
import com.tamimattafi.zennex.model.list.ListItem
import javax.inject.Inject

class AddPresenter @Inject constructor(view: AddContract.View, repository: ListContract.Repository) :
    FormPresenter(view, repository), AddContract.Presenter {

    override fun callRepository(newItem: ListItem) {
        repository.set(newItem)
    }
}
