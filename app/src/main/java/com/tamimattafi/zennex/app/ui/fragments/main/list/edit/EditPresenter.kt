package com.tamimattafi.zennex.app.ui.fragments.main.list.edit

import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormPresenter
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import javax.inject.Inject

class EditPresenter @Inject constructor(view: EditContract.View, repository: RepositoryContract.Base<ListItem>) :
    FormPresenter(view, repository), EditContract.Presenter {

    override fun setItemId(id: Int) {
        item.id = id
    }

    override fun callRepository(newItem: ListItem) {
        repository.update(newItem)
    }
}
