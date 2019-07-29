package com.tamimattafi.zennex.app.ui.fragments.main.list.add

import android.util.Log
import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditPresenter @Inject constructor(override val view: EditContract.View,
                                        val repository : RepositoryContract.Base<ListItem>)
    : BasePresenter<EditContract.View>(view), EditContract.Presenter {

    private var item : ListItem = ListItem()
    set(value) {
        field = value
        Log.e("EditFragment", "item is set")
        view.bindItemName(value.name)
    }

    override fun getItem(id: Int) {
        repository.apply {
            onItemSuccess = { flowable ->
                Log.e("EditFragment", "success is called")
                flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { throwable ->
                        Log.e("EditFragment", "error ${throwable.message}")
                    }
                    .subscribe { listItem ->
                        this@EditPresenter.item = listItem
                        Log.e("EditFragment", "got item with name ${listItem.name}")
                    }
            }
        }.get(id)
    }

    override fun isNameEdited(name: String): Boolean
        = false


    override fun saveItem(name: String) {
        repository.apply {
            onWriteSuccess = {
                view.dismiss()
            }

            onFailure = {
                Log.e("Presenter", "Add item failure, $it")
            }
        }.set(
            item.apply {
                this.name = name
                this.lastModified = DateUtils.getCurrentUnix()
            }
        )
    }

}