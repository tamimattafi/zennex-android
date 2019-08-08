package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import android.os.Bundle
import android.util.Log
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerController
import com.tamimattafi.zennex.app.ui.custom.holders.Actions
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
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
        refresh.setOnClickListener {
            adapter.refresh()
        }

        refresher.setOnRefreshListener {
            refresh.performClick()
        }

        adapter.controller = MvpRecyclerController(recycler)
    }

    override fun onHolderClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        AppUtils.showToast(appContext, itemId.toString())
    }

    override fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        AppUtils.showToast(appContext, itemId.toString())
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
        AppUtils.showToast(appContext, message)
    }

    override fun setRefreshing(refreshing: Boolean) {
        refresher.isRefreshing = refreshing
        recycler.visibility = if (refreshing) View.INVISIBLE else View.VISIBLE
        refresh.isEnabled = !refreshing
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSelected() {
        super.onSelected()
        try {
            setRefreshing(with(adapter) { (isEmpty() && !networkError) })
        } catch (e: Exception) {
            Log.e(fragmentName, "View is not initialized yet")
        }
    }


}