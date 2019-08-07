package com.tamimattafi.zennex.repository.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.ListContract
import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.model.list.ListItemDao
import javax.inject.Inject

@ListScope
class ListRepository @Inject constructor(private val listItemDao: ListItemDao) :
    ListContract.Repository() {

    override fun get(id: Int) {
        ListItemAsync(listItemDao).apply {
            onComplete = {
                onReadComplete?.invoke(it)
            }
        }.execute(id)
    }

    override fun set(item: ListItem) {
        setUpEditAsync(ListEditAsync.INSERT, item)
    }

    override fun delete(item: ListItem) {
        setUpEditAsync(ListEditAsync.DELETE, item)
    }


    override fun update(item: ListItem) {
        setUpEditAsync(ListEditAsync.UPDATE, item)
    }

    private fun setUpEditAsync(type : Int, item : ListItem) {
        ListEditAsync(listItemDao, type).apply {
            onComplete = {
                onWriteComplete?.invoke(it)
            }
        }.execute(item)
    }

    override fun getData() {
        ListDataAsync(listItemDao).apply {
            onComplete = {
                onListReadComplete?.invoke(it)
            }
        }.execute()
    }

}