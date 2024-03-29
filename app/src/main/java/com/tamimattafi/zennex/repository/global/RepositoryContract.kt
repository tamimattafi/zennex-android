package com.tamimattafi.zennex.repository.global

import android.os.AsyncTask
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface RepositoryContract {


    interface Base<T> {
        fun getData()
        fun destroy()
    }

    interface LocalBase<T> : Base<T>, LocalCallBack<T> {
        fun get(id : Int)
        fun set(item : T)
        fun delete(item: T)
        fun update(item: T)

        abstract class LocalRepository<T> : LocalBase<T> {
            override var onListReadComplete: ((it: Flowable<List<T>>) -> Unit)? = null
            override var onWriteComplete: ((Completable) -> Unit)? = null
            override var onReadComplete: ((it: Maybe<T>) -> Unit)? = null


            override fun stopListening() {
                onListReadComplete = null
                onWriteComplete = null
                onReadComplete = null
            }

            override fun destroy() {
                stopListening()
            }
        }

    }

    interface InternetBase<T> : Base<T>, InternetCallBack<T> {
        var paginationSize: Int
        var currentCount: Int
        fun refresh()

        abstract class InternetRepository<T> : InternetBase<T> {
            override var onListReadComplete: ((it: List<T>) -> Unit)? = null
            override var onFailure: ((message: String) -> Unit)? = null
            override var currentCount: Int = 0

            override fun stopListening() {
                onListReadComplete = null
            }

            override fun destroy() {
                stopListening()
                currentCount = 0
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

    interface LocalCallBack<T> {
        var onReadComplete: ((it: Maybe<T>) -> Unit)?
        var onListReadComplete: ((it: Flowable<List<T>>) -> Unit)?
        var onWriteComplete: ((Completable) -> Unit)?
        fun stopListening()
    }

    interface InternetCallBack<T> {
        var onListReadComplete: ((it: List<T>) -> Unit)?
        var onFailure: ((message: String) -> Unit)?
        fun stopListening()
    }
}