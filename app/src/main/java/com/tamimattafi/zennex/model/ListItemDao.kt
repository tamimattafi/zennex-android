package com.tamimattafi.zennex.model

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface ListItemDao {

    @Query("SELECT * FROM list_items ORDER BY lastModified DESC LIMIT :limit OFFSET :offset")
    fun getAllData(limit : Int, offset : Int) : Flowable<List<ListItem>>

    @Query("SELECT * FROM list_items WHERE id = :id")
    fun getItem(id: Int): Maybe<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item : ListItem) : Completable

    @Update
    fun updateItem(item: ListItem) : Completable

    @Delete
    fun deleteItem(item : ListItem) : Completable
}