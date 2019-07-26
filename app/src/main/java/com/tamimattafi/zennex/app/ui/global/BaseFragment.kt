package com.tamimattafi.zennex.app.ui.global

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamimattafi.zennex.utils.AppUtils
import com.tamimattafi.zennex.utils.PhoneUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    abstract val layoutId: Int

    @Inject
    lateinit var appContext : Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId, container, false)

    fun isConnected(): Boolean = if (!PhoneUtils.isConnected(appContext)) {
        view?.let { AppUtils.showSnackBar(it) }
        false
    } else true

}