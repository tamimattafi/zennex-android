package com.tamimattafi.zennex.app.di.modules.fragments.list

import com.tamimattafi.zennex.app.di.scopes.ListScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.edit.EditContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.edit.EditFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.edit.EditPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class EditModule {

    @ListScope
    @Binds
    abstract fun bindEditPresenter(editPresenter: EditPresenter): EditContract.Presenter

    @ListScope
    @Binds
    abstract fun bindEditView(editFragment: EditFragment): EditContract.View
}