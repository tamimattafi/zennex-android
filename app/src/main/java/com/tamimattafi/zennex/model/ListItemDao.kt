package com.tamimattafi.zennex.model

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ListItemDao {

    @Query("SELECT * FROM list ORDER BY lastModified DESC")
    fun getAllData() : Flowable<List<ListItem>>

    @Query("SELECT * FROM list WHERE id = :itemId")
    fun getItem(itemId : Int) : Flowable<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item : ListItem) : Completable

    @Update
    fun updateItem(item: ListItem) : Completable

    @Delete
    fun deleteItem(item : ListItem) : Completable
}