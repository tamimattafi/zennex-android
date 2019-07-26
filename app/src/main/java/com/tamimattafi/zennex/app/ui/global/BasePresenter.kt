package com.tamimattafi.zennex.app.ui.global


abstract class BasePresenter<V> protected constructor(protected val view: V) {

    protected var isViewDestroyed = false

    open fun onDestroyView() { isViewDestroyed = true }
    open fun onDestroy() {}

}