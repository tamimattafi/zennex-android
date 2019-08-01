package com.tamimattafi.zennex.app.ui.fragments.main.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.mvp.recycler.MvpLocalRecyclerPresenter
import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.repository.global.RepositoryContract
import com.tamimattafi.zennex.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ListScope
class ListPresenter @Inject constructor(
    override val view : ListContract.View,
    repository: ListContract.Repository
) : MvpLocalRecyclerPresenter<ListItem, ListContract.View, ListContract.ListItemHolder>(view, repository),
    ListContract.Presenter {

    override fun bindViewHolder(holder: ListContract.ListItemHolder) {
        if (holder.listPosition in 0 until dataList.size)
            holder.apply {
                with(dataList[listPosition]) {
                    objectId = id
                    setName(name)
                    setDate(DateUtils.toString(lastModified, DateUtils.UI_DATE_PATTERN))
                    setChecked(isChecked)
                }
            }
    }

    override fun getItemName(listPosition: Int): String {
        return dataList[listPosition].name
    }

    override fun switchItem(listPosition: Int) {
        prepareRepository().update(dataList[listPosition].apply { isChecked = !isChecked })
    }

    private fun prepareRepository(): RepositoryContract.LocalBase<ListItem> = repository.apply {
        onWriteComplete = { completable ->
            completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    view.showError(it.localizedMessage ?: it.toString())
                }
                .subscribe()
        }
    }

    override fun deleteItem(listPosition: Int) {
        prepareRepository().delete(dataList[listPosition])
    }

}