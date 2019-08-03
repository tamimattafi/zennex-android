package com.tamimattafi.zennex.app.ui.fragments.main.language

import com.tamimattafi.zennex.app.ApplicationPreferences

object LanguageValues {


    var LIST: ArrayList<String>? = null

    fun getLanguagesMenu(): ArrayList<String> = LIST ?: ArrayList<String>().apply {
        add(ApplicationPreferences.RU)
        add(ApplicationPreferences.EN)
    }.also { LIST = it }

}