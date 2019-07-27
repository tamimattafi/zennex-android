package com.tamimattafi.zennex.app.di.modules.fragments.list.edit

import com.tamimattafi.zennex.app.di.anotations.FragmentScope
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class EditModule {

    @FragmentScope
    @Binds
    abstract fun bindEditPresenter(editPresenter: EditPresenter) : EditContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @FragmentScope
        @Provides
        fun provideEditFragment() : EditFragment = EditFragment()
    }
}