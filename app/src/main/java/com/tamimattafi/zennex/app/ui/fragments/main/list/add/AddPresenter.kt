package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import com.tamimattafi.zennex.app.ui.fragments.main.list.form.FormPresenter
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import javax.inject.Inject

class AddPresenter @Inject constructor(view: AddContract.View, repository: RepositoryContract.Base<ListItem>) :
    FormPresenter(view, repository), AddContract.Presenter {

    override fun callRepository(newItem: ListItem) {
        repository.set(newItem)
    }
}
