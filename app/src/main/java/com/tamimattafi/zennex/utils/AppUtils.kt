package com.tamimattafi.zennex.utils


import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.tamimattafi.zennex.R


object AppUtils {

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
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


    fun isPermissionGranted(context: Context, permission: String): Boolean = ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED

}