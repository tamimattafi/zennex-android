package com.tamimattafi.zennex.repository.global

import io.reactivex.Completable
import io.reactivex.Flowable

interface RepositoryContract {

    interface Base<T> {
        var pagination : Int
        fun get(id : Int) : Flowable<T>
        fun set(item : T) : Completable
        fun delete(item: T) : Completable
        fun update(item: T) : Completable
        fun getNextPage() : Flowable<List<T>>
    }
}