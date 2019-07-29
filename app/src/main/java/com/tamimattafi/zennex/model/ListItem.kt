package com.tamimattafi.zennex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract

@Entity(tableName = "list_items")
data class ListItem(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    var name : String = "",
    var lastModified : Long = 0,
    var isChecked : Boolean = true
) : MvpRecyclerContract.Object<Int> {

    override fun getObjectId(): Int = id ?: -1
}