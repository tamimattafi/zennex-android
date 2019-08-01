package com.tamimattafi.zennex.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.model.list.ListItemDao
import javax.inject.Singleton

@Singleton
@Database(entities = [ListItem::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun listDao() : ListItemDao
}