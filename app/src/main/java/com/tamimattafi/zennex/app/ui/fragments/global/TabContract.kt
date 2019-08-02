package com.tamimattafi.zennex.app.ui.fragments.global

interface TabContract {

    interface TabController {
        fun goToTab(tab: Int)
    }

    interface TabRequestProvider {
        var tabController: TabController?
        fun attachTabController(tabController: TabController) {
            this.tabController = tabController
        }
    }
}