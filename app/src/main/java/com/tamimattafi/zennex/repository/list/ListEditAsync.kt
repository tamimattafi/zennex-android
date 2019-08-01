package com.tamimattafi.zennex.repository.list

import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.model.list.ListItemDao
import com.tamimattafi.zennex.repository.ApplicationDatabase
import com.tamimattafi.zennex.repository.global.RepositoryContract
import io.reactivex.Completable
import javax.inject.Inject

class ListEditAsync(private val listDao: ListItemDao, private val actionType: Int) :
    RepositoryContract.Async<ListItem, Completable>() {

    @Inject
    lateinit var database: ApplicationDatabase

    @Throws(IllegalArgumentException::class)
    override fun doInBackground(vararg p0: ListItem): Completable {
        listDao.run {
            with(p0[0]) {
                return when (actionType) {
                    INSERT -> insertItem(this)
                    DELETE -> deleteItem(this)
                    UPDATE -> updateItem(this)
                    else -> {
                        throw IllegalArgumentException("Unknown Type")
                    }
                }
            }
        }
    }


    companion object TYPES {
        const val INSERT = 0
        const val DELETE = 1
        const val UPDATE = 2
    }
}