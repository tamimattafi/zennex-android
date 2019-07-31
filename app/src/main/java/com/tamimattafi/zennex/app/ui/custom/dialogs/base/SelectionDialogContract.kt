package com.tamimattafi.zennex.app.ui.custom.dialogs.base

interface SelectionDialogContract {

    interface SelectionDialog<OBJECT, HOLDER : SelectionDialogHolder<OBJECT>> {
        fun getSelectionAdapter(
            data: ArrayList<OBJECT>,
            listener: ListDialogActionListener<OBJECT>
        ): SelectionAdapter<OBJECT, HOLDER>

        fun bindData(data: ArrayList<OBJECT>, listener: ListDialogActionListener<OBJECT>)
    }

    interface SelectionDialogHolder<OBJECT> {
        fun bindListeners(listener: ListDialogActionListener<OBJECT>)
        fun bindData(item: OBJECT)
    }

    interface ListDialogActionListener<OBJECT> {
        fun onItemSelected(item: OBJECT)
    }
}