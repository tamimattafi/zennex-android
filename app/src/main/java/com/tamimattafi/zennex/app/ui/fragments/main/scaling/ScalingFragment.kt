package com.tamimattafi.zennex.app.ui.fragments.main.scaling

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import com.tamimattafi.zennex.app.ui.fragments.main.scaling.preview.PreviewFragment
import kotlinx.android.synthetic.main.fragment_scaling.*
import javax.inject.Inject

class ScalingFragment : NavigationContract.NavigationFragment(), NavigationContract.ActivityResultReceiver {

    @Inject
    lateinit var galleryManager: GalleryManager

    @Inject
    lateinit var cameraManager: CameraManager

    override var fragmentName: String = "fragment-scaling"
    override val layoutId: Int = R.layout.fragment_scaling

    private val REQUEST_TAKE_PHOTO: Int = 0
    private val REQUEST_SELECT_PHOTO: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gallery.setOnClickListener {
            galleryManager.openGallery(REQUEST_SELECT_PHOTO)
        }

        camera.setOnClickListener {
            cameraManager.openCamera(REQUEST_TAKE_PHOTO)
        }
    }

    override fun onReceiveActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            navigationManager.requestAttachScreen(
                PreviewFragment().also {
                    it.prepareImage(
                        when (requestCode) {
                            REQUEST_TAKE_PHOTO -> cameraManager.picturePath
                            REQUEST_SELECT_PHOTO -> data?.data?.toString() ?: return
                            else -> return
                        }
                    )
                }
            )
        }
    }

}