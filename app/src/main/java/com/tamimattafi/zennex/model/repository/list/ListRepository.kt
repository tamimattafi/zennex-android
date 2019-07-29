package com.tamimattafi.zennex.model.repository.list

import android.util.Log
import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.ApplicationDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListRepository @Inject constructor(private val database: ApplicationDatabase) : RepositoryContract.Base.BaseRepository<ListItem>() {

    override var paginationSize = 48

    override fun get(id: Int) {
        ItemAsync(database.listDao()).apply {
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
        EditAsync(database.listDao(), type).apply {
            onComplete = {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        onWriteSuccess?.let { function ->
                            function(it)
                        }
                    }.doOnError { throwable ->
                        onFailure?.let {function ->
                            function(throwable.message!!)
                        }
                    }.subscribe()
            }
        }.execute(item)
    }

    override fun getNextPage() {
        ListAsync(database.listDao()).apply {
            onComplete = {
                onListSuccess?.let { function ->
                    function(it)
                }
            }
        }.execute(Pair(paginationSize, currentItemCount))
    }



}