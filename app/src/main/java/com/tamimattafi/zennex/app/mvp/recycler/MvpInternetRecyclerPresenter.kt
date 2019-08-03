package com.tamimattafi.zennex.app.mvp.recycler

import com.tamimattafi.zennex.app.mvp.BasePresenter
import com.tamimattafi.zennex.repository.global.RepositoryContract

abstract class MvpInternetRecyclerPresenter<T : MvpRecyclerContract.Object<Int>,
        VIEW : MvpRecyclerContract.RefreshableView<HOLDER>,
        HOLDER : MvpRecyclerContract.Holder>(
    override var view: VIEW?,
    protected val repository: RepositoryContract.InternetBase<T>
) : BasePresenter<VIEW>(view), MvpRecyclerContract.InternetPresenter<HOLDER> {

    protected var dataList: ArrayList<T> = ArrayList()

    override fun loadMoreRecyclerData(recycler: MvpRecyclerContract.RecyclerAdapter<HOLDER>) {
        with(recycler) {
            if (!allData && !isLoading) {

                isLoading = true

                repository.apply {

                    onListReadComplete = {
                        isLoading = true
                        dataList.addAll(ArrayList(it))
                        setDataCount(dataList.size)
                        allData = it.size < paginationSize
                        isLoading = false
                        view?.setRefreshing(false)
                    }

                    onFailure = {
                        (recycler as? MvpRecyclerContract.InternetRecyclerAdapter<HOLDER>)?.networkError = true
                        isLoading = false
                        setDataCount(dataList.size)
                        view?.showError(it)
                    }

                }.getData()

            }

        }
    }

    override fun refresh(recycler: MvpRecyclerContract.RecyclerAdapter<HOLDER>) {
        dataList.clear()
        repository.refresh()
        loadMoreRecyclerData(recycler)
    }

    override fun onDestroy() {
        repository.stopListening()
        dataList.clear()
    }

}