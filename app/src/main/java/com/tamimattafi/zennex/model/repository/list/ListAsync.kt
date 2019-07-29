package com.tamimattafi.zennex.model.repository.list

import android.util.Log
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.ListItemDao
import io.reactivex.Flowable

class ListAsync(private val listDao : ListItemDao) : RepositoryContract.Async<Pair<Int, Int>, Flowable<List<ListItem>>>() {

    override fun doInBackground(vararg params: Pair<Int, Int>): Flowable<List<ListItem>>
           = with(params[0]) {
                    Log.e("ListAsync", "Getting all data pagination $first count $second")
                    listDao.getAllData(first, second)
                }

}