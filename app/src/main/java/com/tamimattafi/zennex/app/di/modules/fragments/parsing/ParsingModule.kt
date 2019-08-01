package com.tamimattafi.zennex.app.di.modules.fragments.parsing

import com.tamimattafi.zennex.app.di.scopes.ParsingScope
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingAdapter
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingContract
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingFragment
import com.tamimattafi.zennex.app.ui.fragments.main.parsing.ParsingPresenter
import com.tamimattafi.zennex.repository.parsing.ParsingRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ParsingModule {

    @ParsingScope
    @Binds
    abstract fun bindParsingPresenter(parsingPresenter: ParsingPresenter): ParsingContract.Presenter

    @ParsingScope
    @Binds
    abstract fun bindParsingAdapter(parsingAdapter: ParsingAdapter): ParsingContract.Adapter

    @ParsingScope
    @Binds
    abstract fun bindParsingView(parsingFragment: ParsingFragment): ParsingContract.View

    @ParsingScope
    @Binds
    abstract fun bindParsingRepository(parsingRepository: ParsingRepository): ParsingContract.Repository

}