package com.tamimattafi.zennex.repository.list

import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.model.list.ListItemDao
import com.tamimattafi.zennex.repository.global.RepositoryContract
import io.reactivex.Flowable

class ListDataAsync(private val listDao: ListItemDao) :
    RepositoryContract.Async<Pair<Int, Int>, Flowable<List<ListItem>>>() {

    override fun doInBackground(vararg params: Pair<Int, Int>): Flowable<List<ListItem>> = listDao.getAllData()

}