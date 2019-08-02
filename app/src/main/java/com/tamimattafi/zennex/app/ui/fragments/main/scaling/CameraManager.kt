package com.tamimattafi.zennex.app.ui.fragments.main.scaling

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import com.tamimattafi.zennex.utils.DateUtils
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CameraManager @Inject constructor(
    private val navigationManager: NavigationContract.NavigationManager,
    private val resultReceiver: NavigationContract.ActivityResultReceiver,
    private val activity: Activity
) {

    lateinit var picturePath: String

    fun openCamera(requestCode: Int) {
        if (checkPermission()) {
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
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val imageFileName = "ZENNEX_" + DateUtils.getCurrentUnix() + "_"
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

    private fun checkPermission(): Boolean = (AppUtils.PERMISSION_GRANTED == AppUtils.isPermissionGranted(
        activity,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
            and AppUtils.isPermissionGranted(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE))

}