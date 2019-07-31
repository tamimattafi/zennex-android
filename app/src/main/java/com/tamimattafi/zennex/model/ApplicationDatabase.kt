package com.tamimattafi.zennex.model

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [ListItem::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun listDao() : ListItemDao


    companion object {
        const val NAME = "app-database"
    }
}