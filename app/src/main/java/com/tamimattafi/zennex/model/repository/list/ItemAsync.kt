package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import io.reactivex.Flowable
import java.lang.NullPointerException
import javax.inject.Inject

class ItemAsync : RepositoryContract.Async<Int, Flowable<ListItem>>() {

    @Inject
    lateinit var listItemDao: ListItemDao

    override fun doInBackground(vararg p0: Int?): Flowable<ListItem>
            = p0[0]?.let { listItemDao.getItem(it) } ?: throw NullPointerException("Item do not exist")

}