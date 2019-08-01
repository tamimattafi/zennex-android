package com.tamimattafi.zennex.repository.parsing

import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingContract
import com.tamimattafi.zennex.repository.InternetService
import javax.inject.Inject

@ParsingScope
class ParsingRepository @Inject constructor(private val service: InternetService) : ParsingContract.Repository() {

    override var paginationSize: Int = 50

    override fun refresh() {
        stopListening()
        currentCount = 0
    }

    override fun getData() {
        ParsingDataAsync(service).apply {
            onComplete = {
                onListReadComplete?.invoke(it)
            }
        }.execute(Pair(paginationSize, currentCount))
    }

}