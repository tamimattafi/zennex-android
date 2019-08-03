package com.tamimattafi.zennex.app.ui.fragments.main.scaling

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject

class GalleryManager @Inject constructor(
    private val navigationManager: NavigationContract.NavigationManager,
    private val resultReceiver: NavigationContract.ActivityResultReceiver,
    private val activity: Activity
) {

    private var requestCode: Int = -1

    fun openGallery(requestCode: Int) {
        this.requestCode = requestCode
        checkPermission()
    }

    @SuppressLint("CheckResult")
    private fun checkPermission() {
        RxPermissions(resultReceiver as Fragment)
            .request(Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe { granted ->
                if (granted) {
                    startIntent()
                } else {
                    with(activity) {
                        AppUtils.showToast(this, resources.getString(R.string.permission_was_denied_try_again))
                    }
                }
            }
    }

    private fun startIntent() {
        navigationManager.requestActivityForResult(
            resultReceiver,
            Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
            requestCode
        )
    }


}