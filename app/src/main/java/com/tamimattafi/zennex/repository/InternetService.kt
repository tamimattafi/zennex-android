package com.tamimattafi.zennex.repository

import com.tamimattafi.zennex.model.quote.QuotesPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface InternetService {

    @GET("quotes")
    fun getQuotes(@Query("sort") sortBy: String): Call<QuotesPage>

}