package com.tamimattafi.zennex.app.mvp


abstract class BasePresenter<V> protected constructor(open val view: V) {

    protected var isViewDestroyed = false

    open fun onDestroyView() { isViewDestroyed = true }
    open fun onDestroy() {}

}