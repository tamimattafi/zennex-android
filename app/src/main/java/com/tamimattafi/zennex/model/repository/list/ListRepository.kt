package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import javax.inject.Inject

class ListRepository @Inject constructor() : RepositoryContract.Base.BaseRepository<ListItem>() {

    override fun get(id: Int) {
        ItemAsync().apply {
            onComplete = {
                onItemSuccess?.let { function ->
                    function(it)
                }
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
        EditAsync(type).apply {
            onComplete = {
                onWriteSuccess?.let { function ->
                    function(it)
                }
            }
        }.execute(item)
    }

    override fun getData() {
        ListAsync().apply {
            onComplete = {
                onListSuccess?.let { function ->
                    function(it)
                }
            }
        }.execute()
    }



}