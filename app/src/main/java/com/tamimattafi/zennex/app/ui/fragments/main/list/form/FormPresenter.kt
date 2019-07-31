package com.tamimattafi.zennex.app.ui.fragments.main.list.form

import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class FormPresenter constructor(
    override val view: FormContract.View,
    val repository: RepositoryContract.Base<ListItem>
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