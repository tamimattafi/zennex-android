package com.tamimattafi.zennex.app.mvp.recycler

import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.model.repository.global.RepositoryContract
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class MvpRecyclerPresenter<
        T : MvpRecyclerContract.Object<Int>,
        VIEW : MvpRecyclerContract.View<HOLDER>,
        HOLDER : MvpRecyclerContract.Holder>(
    override val view: VIEW,
    protected val repository: RepositoryContract.Base<T>
)
    : BasePresenter<VIEW>(view), MvpRecyclerContract.Presenter<HOLDER> {

    protected lateinit var data : Flowable<List<T>>
    protected var dataList : ArrayList<T> = ArrayList()

    override fun loadMoreRecyclerData(recycler: MvpRecyclerContract.RecyclerAdapter<HOLDER>) {
        with(recycler) {
            if (!allData && !isLoading) {

                isLoading = true

                repository.apply {

                    onListReadComplete = { newData ->
                        data = (if (::data.isInitialized) {
                            Flowable.merge(data, newData)
                        } else newData).apply {
                            subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnError {
                                    isLoading = false
                                }.doOnNext { list ->
                                    isLoading = true
                                    repository.currentItemCount = list.size
                                    dataList = ArrayList(list)
                                    setDataCount(dataList.size)
                                    allData = list.isEmpty() || (dataList.size % paginationSize) != 0
                                    isLoading = false
                                }
                                .subscribe()
                        }
                    }

                }.getNextPage()

            }

        }
    }

    override fun refresh(recycler: MvpRecyclerContract.RecyclerAdapter<HOLDER>) {
        repository.refresh()
        loadMoreRecyclerData(recycler)
    }

    override fun onDestroy() {
        dataList.clear()
        repository.stopListening()
    }

}