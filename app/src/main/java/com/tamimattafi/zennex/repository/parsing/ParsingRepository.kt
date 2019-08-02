package com.tamimattafi.zennex.repository.parsing

import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingContract
import com.tamimattafi.zennex.model.quote.QuotesPage
import com.tamimattafi.zennex.repository.InternetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ParsingScope
class ParsingRepository @Inject constructor(private val service: InternetService) : ParsingContract.Repository() {

    override var paginationSize: Int = 50

    override fun refresh() {
        stopListening()
        currentCount = 0
    }

    override fun getData() {
        service.getQuotes(ParsingValues.TIME).enqueue(object : Callback<QuotesPage> {
            override fun onFailure(call: Call<QuotesPage>, t: Throwable) {
                onFailure?.invoke(t.localizedMessage ?: t.toString())
            }

            override fun onResponse(call: Call<QuotesPage>, response: Response<QuotesPage>) {
                onListReadComplete?.invoke(response.body()?.quotes ?: listOf())
            }

        })
    }

}