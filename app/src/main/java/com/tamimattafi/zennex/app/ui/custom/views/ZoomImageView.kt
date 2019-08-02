package com.tamimattafi.zennex.app.ui.custom.views

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import kotlin.math.atan2
import kotlin.math.sqrt

class ZoomImageView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet) {

    private var isZoomAndRotate: Boolean = false
    private var isOutSide: Boolean = false

    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2

    private var mode = NONE

    private val start = PointF()
    private val mid = PointF()

    private var lastEvent: FloatArray? = FloatArray(4)
    private var d = 0f
    private var newRot = 0f
    private var oldDist = 1f
    private var xCoOrdinate: Float = 0.toFloat()
    private var yCoOrdinate: Float = 0.toFloat()

    init {
        setOnTouchListener { _, event ->
            this.bringToFront()
            viewTransformation(event)
            true
        }
    }

    private fun viewTransformation(event: MotionEvent) {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                xCoOrdinate = x - event.rawX
                yCoOrdinate = y - event.rawY

                start.set(event.x, event.y)
                isOutSide = false
                mode = DRAG
                lastEvent = null
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                if (oldDist > 10f) {
                    midPoint(mid, event)
                    mode = ZOOM
                }

                lastEvent = FloatArray(4).apply {
                    this[0] = event.getX(0)
                    this[1] = event.getX(1)
                    this[2] = event.getY(0)
                    this[3] = event.getY(1)
                }

                d = rotation(event)
            }
            MotionEvent.ACTION_UP -> {
                isZoomAndRotate = false
                if (mode == DRAG) {
                    val x = event.x
                    val y = event.y
                }
                isOutSide = true
                mode = NONE
                lastEvent = null
                mode = NONE
                lastEvent = null
            }
            MotionEvent.ACTION_OUTSIDE -> {
                isOutSide = true
                mode = NONE
                lastEvent = null
                mode = NONE
                lastEvent = null
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
                lastEvent = null
            }
            MotionEvent.ACTION_MOVE -> if (!isOutSide) {
                if (mode == DRAG) {
                    isZoomAndRotate = false
                    animate().x(event.rawX + xCoOrdinate).y(event.rawY + yCoOrdinate).setDuration(0).start()
                }
                if (mode == ZOOM && event.pointerCount == 2) {
                    val newDist1 = spacing(event)
                    if (newDist1 > 10f) {
                        val scale = newDist1 / oldDist * scaleX
                        scaleX = scale
                        scaleY = scale
                    }
                    if (lastEvent != null) {
                        newRot = rotation(event)
                        rotation = (this.rotation + (newRot - d))
                    }
                }
            }
        }
    }

    private fun rotation(event: MotionEvent): Float {
        val deltaX = (event.getX(0) - event.getX(1)).toDouble()
        val deltaY = (event.getY(0) - event.getY(1)).toDouble()
        val radians = atan2(deltaY, deltaX)
        return Math.toDegrees(radians).toFloat()
    }

    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x + y * y).toDouble()).toInt().toFloat()
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point.set(x / 2, y / 2)
    }


}