package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerController
import com.tamimattafi.zennex.app.ui.custom.holders.Actions
import com.tamimattafi.zennex.app.ui.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.fragment_recycler_refresher.*
import kotlinx.android.synthetic.main.toolbar_parsing.*
import javax.inject.Inject

class ParsingFragment : NavigationContract.NavigationFragment(), ParsingContract.View {

    @Inject
    lateinit var presenter: ParsingContract.Presenter

    @Inject
    lateinit var adapter: ParsingContract.Adapter

    override var fragmentName: String = "fragment-parsing"
    override val layoutId: Int = R.layout.fragment_parsing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.controller = MvpRecyclerController(recycler)
        refresh.setOnClickListener {
            adapter.refresh()
        }

        refresher.setOnRefreshListener {
            refresh.performClick()
        }
    }

    override fun onHolderClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        AppUtils.showToast(appContext, "Item $itemId click !")
    }

    override fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        AppUtils.showToast(appContext, "Item $itemId Long click !")
    }

    override fun onHolderAction(listPosition: Int, adapterPosition: Int, action: Int, itemId: Int?) {
        Actions.apply {
            when (action) {
                ACTION_REFRESH -> adapter.refresh()
                ACTION_TRY_AGAIN -> adapter.tryAgain()
            }
        }
    }

    override fun showError(message: String) {
        setRefreshing(false)
        AppUtils.showSnackBar(view!!, message)
    }

    override fun setRefreshing(refreshing: Boolean) {
        with(refresher) {
            if (isRefreshing != refreshing) {
                isRefreshing = refreshing

                recycler.visibility = if (refreshing) View.INVISIBLE else View.VISIBLE
            }
        }
    }


}