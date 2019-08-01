package com.tamimattafi.zennex.repository.parsing

import com.tamimattafi.zennex.model.quote.Quote
import com.tamimattafi.zennex.repository.InternetService
import com.tamimattafi.zennex.repository.global.RepositoryContract

class ParsingDataAsync(private val service: InternetService) : RepositoryContract.Async<Pair<Int, Int>, List<Quote>>() {

    @Throws(NullPointerException::class)
    override fun doInBackground(vararg p0: Pair<Int, Int>): List<Quote> = with(p0[0]) {
        first // could be used for pagination size
        second // could be used for offset
        service.getQuotes(ParsingValues.TIME).execute().body()?.quotes
            ?: throw NullPointerException("Response body is null")
    }

}