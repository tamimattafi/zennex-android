package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import android.view.View
import com.tamimattafi.zennex.app.mvp.recycler.MvpSimpleHolder
import kotlinx.android.synthetic.main.item_view_holder_empty.view.description
import kotlinx.android.synthetic.main.item_view_holder_list_item.view.date
import kotlinx.android.synthetic.main.item_view_holder_quote.view.*

class ParsingHolder(itemView: View) : MvpSimpleHolder(itemView), ParsingContract.QuoteHolder {

    override var objectId: Int? = 0

    override fun setDescription(description: String) {
        itemView.description.text = description
    }

    override fun setDate(date: String) {
        itemView.date.text = date
    }

    override fun setRatings(ratings: String) {
        itemView.ratings.text = ratings
    }

}