package com.tamimattafi.zennex.app.ui.fragments.main.scaling

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import com.tamimattafi.zennex.utils.DateUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CameraManager @Inject constructor(
    private val navigationManager: NavigationContract.NavigationManager,
    private val resultReceiver: NavigationContract.ActivityResultReceiver,
    private val activity: Activity
) {

    private var requestCode: Int = -1
    lateinit var picturePath: String

    fun openCamera(requestCode: Int) {
        this.requestCode = requestCode
        checkPermission()
    }

    @SuppressLint("CheckResult")
    private fun checkPermission() {
        RxPermissions(resultReceiver as Fragment)
            .request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
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
        with(Intent(MediaStore.ACTION_IMAGE_CAPTURE)) {
            if (resolveActivity(activity.packageManager) != null) {
                try {
                    createImageFile()
                } catch (ex: IOException) {
                    AppUtils.showToast(activity, ex.localizedMessage)
                    null
                }?.let { file ->
                    putExtra(
                        MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                            activity,
                            activity.packageName + ".utilities.GenericFileProvider",
                            file
                        )
                    )

                    navigationManager.requestActivityForResult(
                        resultReceiver,
                        this,
                        requestCode
                    )
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val imageFileName = "ZENNEX_" + DateUtils.getCurrentUnix() + "_JPEG"
        val storageDir = activity.getExternalFilesDir(
            Environment.DIRECTORY_PICTURES
        )
        return File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        ).also {
            picturePath = "file://" + it.absolutePath
        }
    }

}