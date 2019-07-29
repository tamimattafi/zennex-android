package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.ListItemDao
import io.reactivex.Flowable
import java.lang.NullPointerException

class ItemAsync(private val listDao: ListItemDao) : RepositoryContract.Async<Int, Flowable<ListItem>>() {


    override fun doInBackground(vararg p0: Int?): Flowable<ListItem>
            = p0[0]?.let { listDao.getItem(it) } ?: throw NullPointerException("Item do not exist")

}