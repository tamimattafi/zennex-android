package com.tamimattafi.zennex.app.ui.fragments.main.list

import com.tamimattafi.zennex.app.mvp.recycler.MvpLocalRecyclerAdapter
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.model.list.ListItem
import com.tamimattafi.zennex.repository.global.RepositoryContract

interface ListContract {

    interface Presenter : MvpRecyclerContract.Presenter<ListItemHolder> {
        fun getItemName(listPosition: Int): String
        fun switchItem(listPosition: Int)
        fun deleteItem(listPosition: Int)
    }

    interface View : MvpRecyclerContract.View<ListItemHolder>

    abstract class Adapter(
        presenter: MvpRecyclerContract.Presenter<ListItemHolder>,
        listener: MvpRecyclerContract.Listener
    ) : MvpLocalRecyclerAdapter<ListItemHolder>(presenter, listener)

    abstract class Repository : RepositoryContract.LocalBase.LocalRepository<ListItem>()

    interface ListItemHolder : MvpRecyclerContract.Holder {
        fun setName(name : String)
        fun setDate(date : String)
        fun setChecked(isChecked : Boolean)
    }


    interface MyView {
        fun setText(string: String)
    }

    class MyViewImp : MyView {

        lateinit var presenter: MyPresenter
        var name: String = ""

        override fun setText(string: String) {
            name = string
        }

        //

        fun click() {
            presenter.getText()
        }

    }

    interface MyPresenter {
        fun getText()
    }


    class MyPresenterImp(private val view: MyView) : MyPresenter {
        override fun getText() {
            //..
            //..

            view.setText("dsfsdfsdf")
        }

    }


}