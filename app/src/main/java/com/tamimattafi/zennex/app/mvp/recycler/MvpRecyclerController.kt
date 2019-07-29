package com.tamimattafi.zennex.app.mvp.recycler

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.decorators.ItemOffsetDecoration

open class MvpRecyclerController<HOLDER : MvpRecyclerContract.Holder>(override val recycler: RecyclerView) : MvpRecyclerContract.RecyclerController<HOLDER> {

    open fun onSetRecyclerSpan(): Int = 1

    lateinit var layoutManager: GridLayoutManager

    override fun prepare(adapter: MvpRecyclerContract.RecyclerAdapter<HOLDER>): Boolean {
        with(recycler) {
            this@with.layoutManager = GridLayoutManager(
                context!!,
                onSetRecyclerSpan()
            ).also { setUpLayoutManagerSpan(it) }
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(onSetRecyclerDecorator())
        }

        return addRecyclerScrollListener(adapter, recycler)

    }


    override fun getViewHolder(position: Int): HOLDER?
            = layoutManager.findViewByPosition(position) as? HOLDER


    open fun addRecyclerScrollListener(adapter : MvpRecyclerContract.RecyclerAdapter<HOLDER>, recyclerView: RecyclerView) : Boolean {
        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    with(adapter) {
                        if (dy > 0 && !isLoading && !allData) {
                            (recyclerView.layoutManager as? GridLayoutManager)?.apply {
                                if (findLastVisibleItemPosition() >= itemCount * 90 / 100) {
                                    loadMore()
                                }
                            }
                        }
                    }
                }
            }
        )
        return true
    }

    open fun onSetRecyclerDecorator(): RecyclerView.ItemDecoration =
        ItemOffsetDecoration(recycler.context, R.dimen.item_spacing)

    open fun setUpLayoutManagerSpan(layoutManager: GridLayoutManager) {
        this.layoutManager = layoutManager
        with(layoutManager) {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (itemCount) {
                        1 -> spanCount
                        else -> 1
                    }
                }
            }
        }
    }


}