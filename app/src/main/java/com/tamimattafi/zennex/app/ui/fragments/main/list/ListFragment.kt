package com.tamimattafi.zennex.app.ui.fragments.main.list

import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.mvp.recycler.MvpRecyclerController
import com.tamimattafi.zennex.app.ui.custom.dialogs.base.SelectionDialogContract
import com.tamimattafi.zennex.app.ui.custom.dialogs.model.MenuItemData
import com.tamimattafi.zennex.app.ui.custom.dialogs.sub.MenuBottomSelectionDialog
import com.tamimattafi.zennex.app.ui.custom.holders.Actions
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.app.ui.fragments.main.list.add.AddFragment
import com.tamimattafi.zennex.app.ui.fragments.main.list.edit.EditFragment
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.toolbar_list.*
import javax.inject.Inject

class ListFragment : NavigationContract.NavigationFragment(), ListContract.View {

    @Inject
    lateinit var presenter : ListContract.Presenter

    @Inject
    lateinit var adapter: ListContract.Adapter

    override var fragmentName: String = "fragment-map"
    override val layoutId: Int = R.layout.fragment_list


    private val bottomDialog by lazy {
        MenuBottomSelectionDialog(context!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.controller = MvpRecyclerController(recycler)
        add.setOnClickListener {
            navigationManager.requestSlideLeftScreen(AddFragment())
        }
    }

    override fun onHolderClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        navigationManager.requestSlideRightScreen(
            EditFragment(presenter.getItemName(listPosition), itemId ?: return)
        )
    }

    override fun onHolderAction(listPosition: Int, adapterPosition: Int, action: Int, itemId: Int?) {
        Actions.apply {
            when (action) {
                ACTION_CREATE -> add.performClick()
                ACTION_SWITCH -> presenter.switchItem(listPosition)
            }
        }
    }

    override fun onHolderLongClick(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        showContextDialog(listPosition, adapterPosition, itemId)
    }

    private fun showContextDialog(listPosition: Int, adapterPosition: Int, itemId: Int?) {
        with(ListValues) {
            bottomDialog.apply {
                bindData(
                    getContextMenu(context),
                    object : SelectionDialogContract.ListDialogActionListener<MenuItemData> {
                        override fun onItemSelected(item: MenuItemData) {
                            when (item.id) {
                                EDIT_ITEM -> onHolderClick(listPosition, adapterPosition, itemId)
                                DELETE_ITEM -> presenter.deleteItem(listPosition)
                            }
                            dismiss()
                        }
                    }
                )
            }.show()
        }
    }

    override fun showError(message: String) {
        AppUtils.showToast(appContext, message)
    }

}