package com.tamimattafi.zennex.app.ui.fragments.main.scaling

import android.app.Activity
import android.content.Intent
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import javax.inject.Inject

class GalleryManager @Inject constructor(
    private val navigationManager: NavigationContract.NavigationManager,
    private val resultReceiver: NavigationContract.ActivityResultReceiver,
    private val activity: Activity
) {

    fun openGallery(requestCode: Int) {
        if (checkPermission()) {
            with(Intent()) {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
                navigationManager.requestActivityForResult(
                    resultReceiver,
                    Intent.createChooser(this, "Select Picture"),
                    requestCode
                )
            }
        }
    }

    private fun checkPermission(): Boolean = AppUtils.isPermissionGranted(
        activity,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    ) == AppUtils.PERMISSION_GRANTED

}