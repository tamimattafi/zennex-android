package com.tamimattafi.zennex.app

import android.content.Context
import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class ApplicationPreferences @Inject constructor(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)

    fun setLanguage(language: String) {
        with(preferences.edit()) {
            putString("lang", language)
            apply()
        }
    }

    fun getLanguage(): String = preferences.getString("lang", DEFAULT) ?: DEFAULT


    companion object {
        const val RU = "ru"
        const val EN = "en"
        val DEFAULT: String
            get() = Locale.getDefault().language
    }
}