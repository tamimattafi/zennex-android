package com.tamimattafi.zennex.app.di.modules.fragments.list.edit

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class EditModule {

    @ListScope @Binds
    abstract fun bindEditPresenter(editPresenter: EditPresenter) : EditContract.Presenter

    @ListScope @Binds
    abstract fun bindEditView(editFragment: EditFragment) : EditContract.View
}