package com.tamimattafi.zennex.app.ui.fragments.main.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.item_view_holder_empty.*
import javax.inject.Inject


class MapFragment : NavigationContract.NavigationFragment(), OnMapReadyCallback {

    override var fragmentName: String = "fragment-map"
    override val layoutId: Int = R.layout.fragment_map

    @Inject
    lateinit var mapManager: MapManager

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)?.let {
            with(mapManager) {
                attachMapFragment(it)
                if (mapManager.hasPermissions()) {
                    permission.visibility = View.GONE
                    setCameraPositionListener { longitude, latitude ->
                        with(location) {
                            visibility = View.VISIBLE
                            text = appContext.resources.getString(R.string.location) + "$latitude, $longitude"
                        }
                    }
                    setUpMap()

                } else {
                    setUpPermissionLayout()
                    addPermissionListener(
                        onGrant = {
                            permission.visibility = View.GONE
                        },

                        onDenied = {
                            with(appContext) {
                                AppUtils.showToast(
                                    this,
                                    resources.getString(R.string.permissions_are_required)
                                )
                            }
                            permission.visibility = View.VISIBLE
                        }
                    )

                }
            }
        }
    }

    private fun setUpPermissionLayout() {

        permission.visibility = View.VISIBLE
        image.setImageResource(R.drawable.ic_placeholder_map)
        with(appActivity.resources) {
            label.text = getString(R.string.permission_required)
            description.text = getString(R.string.permission_must_be_granted)
            action.apply {
                text = getString(R.string.grant_permission)
                setOnClickListener {
                    mapManager.checkPermissions()
                }
            }
        }

    }

    override fun onMapReady(map: GoogleMap?) {

    }
}