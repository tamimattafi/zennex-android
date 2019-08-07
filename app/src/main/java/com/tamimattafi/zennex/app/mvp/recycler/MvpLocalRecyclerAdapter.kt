package com.tamimattafi.zennex.app.mvp.recycler

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolder
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolderList
import com.tamimattafi.zennex.app.ui.custom.holders.empty.UnbindableHolder
import javax.inject.Inject

abstract class MvpLocalRecyclerAdapter<HOLDER : MvpRecyclerContract.Holder>(
    open val presenter: MvpRecyclerContract.Presenter<HOLDER>,
    val listener: MvpRecyclerContract.Listener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MvpRecyclerContract.RecyclerAdapter<HOLDER> {

    @Inject
    lateinit var activity: Activity

    protected var dataCount : Int = 0

    open var headersCount = 0
    open var footersCount = 0

    override var allData: Boolean = false
    override var isLoading: Boolean = false


    override var controller: MvpRecyclerContract.RecyclerController<HOLDER>? = null
            set(value) {
                field = value
                if (value != null) {
                    if (value.prepare(this)) {
                        value.recycler.adapter = this
                        loadMore()
                    }
                }
            }

    protected val ITEM_HEADER = Int.MAX_VALUE - 1
    protected val ITEM_NO_DATA = ITEM_HEADER - 1
    protected val ITEM_LOADING = ITEM_NO_DATA - 1
    protected val ITEM_LOADING_ERROR = ITEM_LOADING - 1
    protected val ITEM_MAIN = ITEM_LOADING_ERROR - 1
    protected val ITEM_FOOTER = ITEM_MAIN - 1

    override fun setDataCount(dataCount: Int) : Boolean {
        this.dataCount = dataCount
        notifyDataSetChanged()
        return true
    }

    override fun getViewHolder(listPosition: Int): HOLDER?
            = controller?.getViewHolder(listPosition + headersCount)


    override fun getItemCount(): Int {
        return when {
            dataCount > 0 -> dataCount + footersCount + headersCount + if (allData) 0 else 1
            else -> 1
        }
    }

    override fun loadMore() {
        presenter.loadMoreRecyclerData(this)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MvpRecyclerContract.ListenerHolder) {
            holder.listener = listener
        }
        (holder as? HOLDER)?.apply {
            listPosition = position - headersCount
            presenter.bindViewHolder(this)
        }
    }


    abstract fun getItemHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getNoDataHolderType(): Int

    open fun getNoDataHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return EmptyHolder(
            EmptyHolderList.getItem(activity, type),
            LayoutInflater.from(activity).inflate(
                R.layout.item_view_holder_empty,
                parent,
                false
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        with(LayoutInflater.from(activity)) {
            return when (viewType) {
                ITEM_MAIN -> getItemHolder(parent)
                ITEM_NO_DATA -> getNoDataHolder(parent, getNoDataHolderType())
                else -> UnbindableHolder(
                    inflate(
                        R.layout.item_view_holder_bottom_loading,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            dataCount > 0 && headersCount > 0 && position in 0 until headersCount -> ITEM_HEADER
            dataCount > 0 && position in headersCount until dataCount + headersCount -> ITEM_MAIN
            dataCount > 0 && footersCount > 0 && position in dataCount until (dataCount + footersCount) -> ITEM_FOOTER
            dataCount == 0 && allData -> ITEM_NO_DATA
            else -> ITEM_LOADING
        }
    }

    override fun isEmpty(): Boolean = dataCount == 0

}