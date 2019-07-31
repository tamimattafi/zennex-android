package com.tamimattafi.zennex.app.ui.custom.dialogs.base

import androidx.recyclerview.widget.RecyclerView

abstract class SelectionAdapter<OBJECT, HOLDER : SelectionDialogContract.SelectionDialogHolder<OBJECT>>(
    private val data: ArrayList<OBJECT>,
    private val listener: SelectionDialogContract.ListDialogActionListener<OBJECT>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? HOLDER)?.apply {
            bindData(data[position])
            bindListeners(listener)
        }
    }

    fun addMoreData(data: ArrayList<OBJECT>) {
        data.addAll(data)
        notifyDataSetChanged()
    }

    fun destroy() {
        data.clear()
    }

    override fun getItemCount(): Int = data.size


}