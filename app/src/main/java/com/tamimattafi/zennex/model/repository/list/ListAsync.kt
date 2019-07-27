package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import io.reactivex.Flowable
import javax.inject.Inject

class ListAsync : RepositoryContract.Async<Pair<Int, Int>, Flowable<List<ListItem>>>() {

    @Inject
    lateinit var listItemDao: ListItemDao

    override fun doInBackground(vararg params: Pair<Int, Int>): Flowable<List<ListItem>>
           = listItemDao.getAllData()



}