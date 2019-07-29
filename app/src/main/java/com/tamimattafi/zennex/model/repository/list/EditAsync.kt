package com.tamimattafi.zennex.model.repository.list

import com.tamimattafi.zennex.model.ListItem
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import com.tamimattafi.zennex.model.ApplicationDatabase
import com.tamimattafi.zennex.model.ListItemDao
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class EditAsync(private val listDao : ListItemDao, private val actionType : Int) : RepositoryContract.Async<ListItem, Completable>() {

    @Inject
    lateinit var database: ApplicationDatabase

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