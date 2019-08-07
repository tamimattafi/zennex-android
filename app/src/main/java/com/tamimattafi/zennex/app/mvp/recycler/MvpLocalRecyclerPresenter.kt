package com.tamimattafi.zennex.app.mvp.recycler

import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.repository.global.RepositoryContract
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class MvpLocalRecyclerPresenter<
        T : MvpRecyclerContract.Object<Int>,
        VIEW : MvpRecyclerContract.View<HOLDER>,
        HOLDER : MvpRecyclerContract.Holder>(
    override var view: VIEW,
    protected val repository: RepositoryContract.LocalBase<T>
) : BasePresenter<VIEW>(view), MvpRecyclerContract.Presenter<HOLDER> {

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
                                    view.showError(it.localizedMessage ?: it.toString())

                                }.doOnNext { list ->
                                    isLoading = true
                                    dataList = ArrayList(list)
                                    setDataCount(dataList.size)
                                    allData = true
                                    isLoading = false
                                }
                                .subscribe()
                        }
                    }

                }.getData()

            }

        }
    }

    override fun onDestroyView() {
        repository.stopListening()
        super.onDestroyView()
    }

    override fun onDestroy() {
        dataList.clear()
        repository.destroy()
    }

}