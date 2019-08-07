package com.tamimattafi.zennex.app.ui.fragments.main.scaling.preview

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.tamimattafi.zennex.R
import com.tamimattafi.zennex.app.ui.fragments.global.NavigationContract
import kotlinx.android.synthetic.main.fragment_preview.*
import kotlinx.android.synthetic.main.toolbar_preview.*

class PreviewFragment : NavigationContract.NavigationFragment(), PreviewContract.View {

    private lateinit var imagePath: Uri

    override var fragmentName: String = "fragment-preview"
    override val layoutId: Int = R.layout.fragment_preview

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image.setImageURI(imagePath)

        back.setOnClickListener {
            navigationManager.requestBackPress()
        }

        zoomIn.setOnClickListener {
            image.zoomIn()
        }

        zoomOut.setOnClickListener {
            image.zoomOut()
        }

    }

    override fun prepareImage(path: String) {
        imagePath = Uri.parse(path)
    }
}