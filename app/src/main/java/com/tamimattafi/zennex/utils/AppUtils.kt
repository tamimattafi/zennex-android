package com.tamimattafi.zennex.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.tamimattafi.zennex.R


object AppUtils {

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getDrawable(context: Context, drawableId : Int) : Drawable? {
       return ResourcesCompat.getDrawable(context.resources, drawableId, null)
    }

    @ColorInt
    fun getColor(context: Context, colorId: Int) : Int {
        return ResourcesCompat.getColor(context.resources, colorId, null)
    }

    fun showToast(context: Context, text : String? = null) {
        Toast.makeText(
            context,
            text ?: context.resources.getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }

    fun showSnackBar(view: View, text: String? = null) {
        Snackbar.make(
            view,
            text ?: view.context.resources.getString(R.string.something_went_wrong),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun getDimen(context: Context, dimenId: Int): Int = context.resources.getDimension(dimenId).toInt()

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun resetApp(context: Context): Boolean {
        return (context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
                ).clearApplicationUserData()
    }

    fun openLink(context: Context, link: String?) {
        try {
            with(Intent(Intent.ACTION_VIEW, Uri.parse(link))) {
                addFlags(
                    Intent.FLAG_ACTIVITY_NO_HISTORY
                            or Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                )
                context.startActivity(this)
            }
        } catch (e: Exception) {
            showToast(context)
        }
    }

    fun sendEmail(
        context: Context,
        vararg emails: String?,
        subject: String
    ) {
        try {
            with(Intent(Intent.ACTION_SENDTO)) {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, emails)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                resolveActivity(context.packageManager)?.apply {
                    context.startActivity(this@with)
                } ?: showToast(context)
            }
        } catch (e: Exception) {
            showToast(context)
        }
    }


    const val PERMISSION_GRANTED = 0
    const val PERMISSION_DENIED = 1
    const val PERMISSION_REQUESTING = 2
    const val REQUEST_CODE = 4

    fun isPermissionGranted(context: Activity, permission: String): Int {
        return if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    permission
                )

            ) {
                PERMISSION_DENIED
            } else {
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(permission),
                    REQUEST_CODE
                )
                PERMISSION_REQUESTING
            }
        } else {
            PERMISSION_GRANTED
        }
    }

    private fun uriToBitmap(context: Context, uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

    }
}