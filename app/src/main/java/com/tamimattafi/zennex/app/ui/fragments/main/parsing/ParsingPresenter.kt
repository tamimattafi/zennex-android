package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.mvp.recycler.MvpInternetRecyclerPresenter
import com.tamimattafi.zennex.model.quote.Quote
import com.tamimattafi.zennex.utils.DateUtils
import javax.inject.Inject

@ParsingScope
class ParsingPresenter @Inject constructor(
    override val view: ParsingContract.View,
    repository: ParsingContract.Repository
) : MvpInternetRecyclerPresenter<Quote, ParsingContract.View, ParsingContract.QuoteHolder>(view, repository),
    ParsingContract.Presenter {

    override fun bindViewHolder(holder: ParsingContract.QuoteHolder) {
        if (holder.listPosition in 0 until dataList.size)
            holder.apply {
                with(dataList[listPosition]) {
                    objectId = id
                    setDescription(description)
                    setDate(DateUtils.toString(time, Quote.TIME_PATTERN, DateUtils.UI_DATE_PATTERN))
                    setRatings(rating.toString())
                }
            }
    }

}