package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.ListItemDao
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import javax.inject.Inject

@ListScope
class ListRepository @Inject constructor(private val listItemDao: ListItemDao) :
    RepositoryContract.Base.BaseRepository<ListItem>() {

    override var paginationSize = 48

    override fun get(id: Int) {
        ItemAsync(listItemDao).apply {
            onComplete = {
                onReadComplete?.invoke(it)
            }
        }.execute(id)
    }

    override fun set(item: ListItem) {
        setUpEditAsync(EditAsync.INSERT, item)
    }

    override fun delete(item: ListItem) {
        setUpEditAsync(EditAsync.DELETE, item)
    }


    override fun update(item: ListItem) {
        setUpEditAsync(EditAsync.UPDATE, item)
    }

    private fun setUpEditAsync(type : Int, item : ListItem) {
        EditAsync(listItemDao, type).apply {
            onComplete = {
                onWriteComplete?.invoke(it)
            }
        }.execute(item)
    }

    override fun getNextPage() {
        ListAsync(listItemDao).apply {
            onComplete = {
                onListReadComplete?.invoke(it)
            }
        }.execute(Pair(paginationSize, currentItemCount))
    }



}