package com.tamimattafi.zennex.utils

import android.content.ContentResolver
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.provider.OpenableColumns
import com.tamimattafi.zennex.R
import java.io.File


object FileUtils {

    fun getFileName(context: Context, uri: Uri): String {
        var fileName = context.resources.getString(R.string.unknown)
        when (uri.scheme) {
            ContentResolver.SCHEME_FILE -> {
                fileName = File(uri.path ?: return fileName).name
            }
            ContentResolver.SCHEME_CONTENT -> {
                try {
                    context.contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null
                    )?.apply {
                        if (moveToFirst()) {
                            fileName = getString(getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        }
                        close()
                    }
                } catch (e: Exception) {
                    return fileName
                }
            }
        }
        return fileName
    }

    fun getRingtoneName(context: Context, uri: Uri): String {
        var ringtoneName = "Unknown"
        RingtoneManager.getRingtone(context, uri)?.apply {
            with(getTitle(context)) {
                if (!this.isNullOrEmpty()) {
                    ringtoneName = this
                }
            }
        }
        return ringtoneName
    }
}