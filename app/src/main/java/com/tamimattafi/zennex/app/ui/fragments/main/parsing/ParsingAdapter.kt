package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.ui.custom.holders.empty.EmptyHolderList
import javax.inject.Inject

@ParsingScope
class ParsingAdapter @Inject constructor(presenter: ParsingContract.Presenter, listener: ParsingContract.View) :
    ParsingContract.Adapter(presenter, listener) {

    override fun getItemHolder(parent: ViewGroup): RecyclerView.ViewHolder = ParsingHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_view_holder_quote,
            parent,
            false
        )
    )

    override fun getNoDataHolderType(): Int = EmptyHolderList.NO_CONNECTION


}