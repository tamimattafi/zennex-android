package com.tamimattafi.zennex.model.repository.global

import android.os.AsyncTask
import io.reactivex.Completable
import io.reactivex.Flowable

interface RepositoryContract {

    interface Base<T> : ReadCallback<T>, WriteCallback, FailureCallback<String> {
        fun get(id : Int)
        fun set(item : T)
        fun delete(item: T)
        fun update(item: T)
        fun getData()
        fun stopListening()

        abstract class BaseRepository<T> : Base<T> {
            override var onListSuccess: ((it: Flowable<List<T>>) -> Unit)? = null
            override var onItemSuccess: ((Flowable<T>) -> Unit)? = null
            override var onWriteSuccess: ((it: Completable) -> Unit)? = null
            override var onFailure: ((it: String) -> Unit)? = null


            override fun stopListening() {
                onListSuccess = null
                onItemSuccess = null
                onWriteSuccess = null
                onFailure = null
            }
        }

    }

    abstract class Async<PARAMS, RESULT> : AsyncTask<PARAMS, Int, RESULT>(), CompleteCallback<RESULT> {

        override var onComplete: ((it: RESULT) -> Unit)? = null

        override fun onPostExecute(result: RESULT) {
            super.onPostExecute(result)
            onComplete?.let { it(result) }
        }
    }

    interface CompleteCallback<RESULT> {
        var onComplete : ((it : RESULT) -> Unit)?
    }

    interface ReadCallback<T> {
        var onItemSuccess : ((it : Flowable<T>) -> Unit)?
        var onListSuccess : ((it : Flowable<List<T>>) -> Unit)?
    }

    interface WriteCallback {
        var onWriteSuccess : ((it : Completable) -> Unit)?
    }

    interface FailureCallback<T> {
        var onFailure : ((it : T) -> Unit)?
    }
}