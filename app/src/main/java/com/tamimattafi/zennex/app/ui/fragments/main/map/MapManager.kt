package com.tamimattafi.zennex.app.ui.fragments.main.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.tamimattafi.zennex.utils.AppUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject


class MapManager @Inject constructor(val activity: Activity) {

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var map: GoogleMap

    private var onGrant: (() -> Unit)? = null
    private var onDenied: (() -> Unit)? = null

    private var cameraPositionListener: ((longitude: String, latitude: String) -> Unit)? = null

    fun attachMapFragment(mapFragment: SupportMapFragment) {
        this.mapFragment = mapFragment
    }

    @SuppressLint("CheckResult")
    fun checkPermissions() {
        RxPermissions(mapFragment).request(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).subscribe { granted ->
            if (granted) {
                onGrant?.invoke()
                setUpMap()
            } else {
                onDenied?.invoke()
            }
        }
    }

    fun setUpMap() {
        mapFragment.getMapAsync { map ->
            this.map = map
            map.apply {
                mapType = GoogleMap.MAP_TYPE_NORMAL
                clear()
                if (ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    checkPermissions()
                } else {
                    val df = DecimalFormat("#.##").apply {
                        roundingMode = RoundingMode.CEILING
                    }
                    setOnCameraMoveListener {
                        with(cameraPosition.target) {
                            cameraPositionListener?.invoke(
                                df.format(longitude),
                                df.format(latitude)
                            )
                        }
                    }
                    isMyLocationEnabled = true
                    with(uiSettings) {
                        isCompassEnabled = false

                        isZoomControlsEnabled = true
                        isMyLocationButtonEnabled = true
                    }
                }
            }
        }
    }


    fun hasPermissions(): Boolean = AppUtils.isPermissionGranted(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
            && AppUtils.isPermissionGranted(activity, Manifest.permission.ACCESS_FINE_LOCATION)


    fun addPermissionListener(onGrant: (() -> Unit)? = null, onDenied: (() -> Unit)? = null) {
        this.onGrant = onGrant
        this.onDenied = onDenied
    }


    fun setCameraPositionListener(cameraPositionListener: (longitude: String, latitude: String) -> Unit) {
        this.cameraPositionListener = cameraPositionListener
    }

}