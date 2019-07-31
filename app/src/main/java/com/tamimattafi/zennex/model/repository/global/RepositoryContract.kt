package com.tamimattafi.zennex.model.repository.global

import android.os.AsyncTask
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface RepositoryContract {

    interface Base<T> : RepositoryCallBack<T> {
        var currentItemCount : Int
        var paginationSize : Int
        fun get(id : Int)
        fun set(item : T)
        fun delete(item: T)
        fun update(item: T)
        fun getNextPage()
        fun stopListening()
        fun refresh()

        abstract class BaseRepository<T> : Base<T> {
            override var currentItemCount: Int = 0

            override var onListReadComplete: ((it: Flowable<List<T>>) -> Unit)? = null
            override var onWriteComplete: ((Completable) -> Unit)? = null
            override var onReadComplete: ((it: Maybe<T>) -> Unit)? = null

            override fun refresh() {
                stopListening()
                currentItemCount = 0
            }


            override fun stopListening() {
                onListReadComplete = null
                onWriteComplete = null
                onReadComplete = null
            }
        }

    }

    abstract class Async<PARAMS, RESULT> : AsyncTask<PARAMS, Int, RESULT>(), CompleteCallback<RESULT> {

        override var onComplete: ((it: RESULT) -> Unit)? = null

        override fun onPostExecute(result: RESULT) {
            super.onPostExecute(result)
            onComplete?.invoke(result)
        }
    }

    interface CompleteCallback<RESULT> {
        var onComplete : ((it : RESULT) -> Unit)?
    }

    interface RepositoryCallBack<T> {
        var onReadComplete: ((it: Maybe<T>) -> Unit)?
        var onListReadComplete: ((it: Flowable<List<T>>) -> Unit)?
        var onWriteComplete: ((Completable) -> Unit)?
    }
}