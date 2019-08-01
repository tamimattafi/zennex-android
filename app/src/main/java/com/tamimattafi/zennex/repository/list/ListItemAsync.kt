package com.tamimattafi.zennex.repository.list

import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.model.list.ListItemDao
import com.tamimattafi.zennex.repository.global.RepositoryContract
import io.reactivex.Maybe

class ListItemAsync(private val listDao: ListItemDao) : RepositoryContract.Async<Int, Maybe<ListItem>>() {

    @Throws(NullPointerException::class)
    override fun doInBackground(vararg p0: Int?): Maybe<ListItem>
            = p0[0]?.let { listDao.getItem(it) } ?: throw NullPointerException("Item do not exist")

}