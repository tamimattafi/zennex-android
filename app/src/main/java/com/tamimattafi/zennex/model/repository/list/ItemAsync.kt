package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import io.reactivex.Maybe

class ItemAsync(private val listDao: ListItemDao) : RepositoryContract.Async<Int, Maybe<ListItem>>() {


    override fun doInBackground(vararg p0: Int?): Maybe<ListItem>
            = p0[0]?.let { listDao.getItem(it) } ?: throw NullPointerException("Item do not exist")

}