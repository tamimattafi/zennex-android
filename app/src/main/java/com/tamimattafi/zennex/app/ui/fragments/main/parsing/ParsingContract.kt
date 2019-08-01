package com.tamimattafi.zennex.app.ui.fragments.main.parsing

import com.tamimattafi.zennex.app.mvp.recycler.MvpInternetRecyclerAdapter
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.model.quote.Quote
import com.tamimattafi.zennex.repository.global.RepositoryContract

interface ParsingContract {

    interface Presenter : MvpRecyclerContract.InternetPresenter<QuoteHolder>

    interface View : MvpRecyclerContract.RefreshableView<QuoteHolder>

    abstract class Adapter(
        presenter: MvpRecyclerContract.InternetPresenter<QuoteHolder>,
        listener: MvpRecyclerContract.Listener
    ) : MvpInternetRecyclerAdapter<QuoteHolder>(presenter, listener)

    abstract class Repository : RepositoryContract.InternetBase.InternetRepository<Quote>()

    interface QuoteHolder : MvpRecyclerContract.Holder {
        fun setDescription(description: String)
        fun setDate(date: String)
        fun setRatings(ratings: String)
    }
}