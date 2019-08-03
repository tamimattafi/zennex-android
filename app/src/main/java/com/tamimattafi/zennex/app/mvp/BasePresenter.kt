package com.tamimattafi.zennex.app.mvp


abstract class BasePresenter<V> protected constructor(open var view: V?) : BaseContract.Presenter {

    protected var isViewDestroyed = false

    override fun onDestroyView() {
        this.view = null
    }

    override fun onDestroy() {}

}