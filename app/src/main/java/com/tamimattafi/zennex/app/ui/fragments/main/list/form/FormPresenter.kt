package com.tamimattafi.zennex.app.ui.fragments.main.list.form

import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.repository.global.RepositoryContract
import com.tamimattafi.zennex.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class FormPresenter constructor(
    override var view: FormContract.View,
    val repository: RepositoryContract.LocalBase<ListItem>
) : BasePresenter<FormContract.View>(view), FormContract.Presenter {

    var item: ListItem = ListItem()

    abstract fun callRepository(newItem: ListItem)

    override fun saveItem(name: String) {
        repository.apply {
            onWriteComplete = { completable ->
                completable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.dismiss()
                    }, {
                        view.showError(it.localizedMessage ?: it.toString())
                    })
            }
        }

        callRepository(
            item.apply {
                this.name = name
                lastModified = DateUtils.getCurrentUnix()
            }
        )
    }


}