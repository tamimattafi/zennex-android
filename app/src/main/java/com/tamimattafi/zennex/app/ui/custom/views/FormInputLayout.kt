package com.tamimattafi.zennex.app.ui.custom.views

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.AttributeSet
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import com.google.android.material.textfield.TextInputLayout


class FormInputLayout(context: Context, attributeSet: AttributeSet) : TextInputLayout(context, attributeSet) {

    private val shakeAnimation by lazy {
        TranslateAnimation(0F, 10F, 0F, 0F).apply {
            duration = 200
            interpolator = CycleInterpolator(3F)
        }
    }

    private val vibrator by lazy {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun setError(errorText: CharSequence?) {
        super.setError(errorText)
        if (!errorText.isNullOrEmpty()) {
            shake()
            vibrate(50)
        }
    }

    fun shake() {
        startAnimation(shakeAnimation)
    }

    fun vibrate(durationInMillis: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(durationInMillis, VibrationEffect.DEFAULT_AMPLITUDE))
        } else @Suppress("DEPRECATION") {
            vibrator.vibrate(durationInMillis)
        }
    }
}