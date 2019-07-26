package com.tamimattafi.zennex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list")
data class ListItem(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val name : String = "",
    val lastModified : Long = 0,
    val type : Int = IMAGE
) {
    companion object TYPES {
        const val IMAGE = 0
        const val CHECKABLE = 1
    }
}