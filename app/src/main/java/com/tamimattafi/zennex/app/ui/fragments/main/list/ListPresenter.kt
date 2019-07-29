package com.tamimattafi.zennex.app.ui.fragments.main.list

import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerPresenter
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.utils.DateUtils
import javax.inject.Inject

class ListPresenter @Inject constructor(
    override val view : ListContract.View,
    repository: RepositoryContract.Base<ListItem>
) : MvpRecyclerPresenter<ListItem, ListContract.View, ListContract.ListItemHolder>(view, repository), ListContract.Presenter {

    override fun bindViewHolder(holder: ListContract.ListItemHolder) {
        if (holder.listPosition in 0 until  dataList.size)
            holder.apply {
                with(dataList[listPosition]) {
                    objectId = id
                    setName(name)
                    setDate(DateUtils.toString(lastModified, DateUtils.UI_DATE_PATTERN))
                    setChecked(isChecked)
                }
            }
    }
}