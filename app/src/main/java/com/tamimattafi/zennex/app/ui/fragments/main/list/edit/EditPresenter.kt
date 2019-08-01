package com.tamimattafi.zennex.app.ui.fragments.main.list.edit

import com.tamimattafi.zennex.app.ui.fragments.main.list.ListContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormPresenter
import com.tamimattafi.zennex.model.list.ListItem
import javax.inject.Inject

class EditPresenter @Inject constructor(view: EditContract.View, repository: ListContract.Repository) :
    FormPresenter(view, repository), EditContract.Presenter {

    override fun setItemId(id: Int) {
        item.id = id
    }

    override fun callRepository(newItem: ListItem) {
        repository.update(newItem)
    }
}
