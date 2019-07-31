package com.tamimattafi.zennex.app.di.modules.fragments.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.AddContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.AddFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.AddPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class AddModule {

    @ListScope
    @Binds
    abstract fun bindAddPresenter(addPresenter: AddPresenter): AddContract.Presenter

    @ListScope
    @Binds
    abstract fun bindAddView(addFragment: AddFragment): AddContract.View
}