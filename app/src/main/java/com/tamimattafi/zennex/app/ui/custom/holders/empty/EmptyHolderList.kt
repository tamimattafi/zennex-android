package com.tamimattafi.zennex.app.ui.custom.holders.empty

import android.content.Context
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.holders.Actions

object EmptyHolderList {

    const val EMPTY_LIST = 0
    const val NO_CONNECTION = 1
    const val TRY_AGAIN = 2


    private fun getList(context: Context): ArrayList<EmptyHolderData> = with(context.resources) {
        ArrayList<EmptyHolderData>()
            .apply {
                add(
                    EmptyHolderData(
                        EMPTY_LIST,
                        R.drawable.placeholder_empty,
                        getString(R.string.no_data),
                        getString(R.string.no_data_yet),
                        Actions.ACTION_CREATE,
                        getString(R.string.create)
                    )
                )
                add(
                    EmptyHolderData(
                        NO_CONNECTION,
                        R.drawable.placeholder_error,
                        getString(R.string.no_connection),
                        getString(R.string.device_not_connected),
                        Actions.ACTION_REFRESH,
                        getString(R.string.refresh)
                    )
                )
                add(
                    EmptyHolderData(
                        TRY_AGAIN,
                        R.drawable.placeholder_error,
                        getString(R.string.something_went_wrong),
                        getString(R.string.something_went_wrong_internet_connection),
                        Actions.ACTION_TRY_AGAIN,
                        getString(R.string.try_again)
                    )
                )
            }
    }

    fun getItem(context: Context, itemId: Int): EmptyHolderData =
        getList(context)[itemId]
}