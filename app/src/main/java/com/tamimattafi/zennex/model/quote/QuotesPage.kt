package com.tamimattafi.zennex.model.quote


data class QuotesPage(
    val total: Int = 0,
    val last: String = "",
    val quotes: List<Quote>
)

