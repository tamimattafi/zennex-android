package com.tamimattafi.zennex.app.mvp

interface BaseContract {

    interface Presenter {
        fun onDestroyView()
        fun onDestroy()
    }
}