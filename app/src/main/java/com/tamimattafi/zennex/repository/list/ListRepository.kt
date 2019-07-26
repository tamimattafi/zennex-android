package com.tamimattafi.zennex.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.repository.global.RepositoryContract
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ListRepository @Inject constructor(private val listItemDao: ListItemDao) : RepositoryContract.Base<ListItem> {

    override var pagination: Int = 48
    private var currentCount : Int = 0

    override fun get(id: Int): Flowable<ListItem>
            = listItemDao.getItem(id)

    override fun set(item: ListItem): Completable
            = listItemDao.insertItem(item)

    override fun delete(item: ListItem): Completable
            = listItemDao.deleteItem(item)

    override fun update(item: ListItem): Completable
            = listItemDao.updateItem(item)

    override fun getNextPage(): Flowable<List<ListItem>>
            = listItemDao.getPage(pagination, currentCount)


}