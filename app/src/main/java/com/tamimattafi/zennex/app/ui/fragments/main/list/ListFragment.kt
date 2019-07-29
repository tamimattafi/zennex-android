package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerContract
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerController
import com.tamimattafi.zennex.app.ui.custom.holders.Actions
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.EditFragment
import com.tamimattafi.zennex.app.ui.global.NavigationContract
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.toolbar_list.*
import javax.inject.Inject

class ListFragment : NavigationContract.NavigationFragment(), ListContract.View {


    override var fragmentName: String = "fragment-map"
    override val layoutId: Int = R.layout.fragment_list

    @Inject
    lateinit var presenter : ListContract.Presenter

    @Inject
    lateinit var adapter : MvpRecyclerContract.RecyclerAdapter<ListContract.ListItemHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.controller = MvpRecyclerController(recycler)
        add.setOnClickListener {
            navigationManager.requestAttachScreen(EditFragment())
        }
    }

    override fun onHolderClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        navigationManager.requestAttachScreen(
            EditFragment().apply {
                editItem(itemId ?: return)
            }
        )
    }

    override fun onHolderAction(adapterPosition: Int, action: Int, itemId: Int?) {
        if (action == Actions.ACTION_CREATE) {
            add.performClick()
        }
    }

    override fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {

    }

    override fun showError(message: String) {

    }

}