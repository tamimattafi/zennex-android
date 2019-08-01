package com.tamimattafi.zennex.model.quote

import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract

data class Quote(
    val id: Int = 0,
    val description: String = "",
    val time: String = "",
    val rating: Int = 0
) : MvpRecyclerContract.Object<Int> {

    override fun getObjectId(): Int = id

    companion object {
        const val TIME_PATTERN = "yyyy-mm-dd HH:mm:ss"
    }
}