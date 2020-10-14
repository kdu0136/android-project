package kim.dongun.paletteapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import kim.dongun.paletteapp.MainViewModel
import kim.dongun.paletteapp.PermissionHelper
import kim.dongun.paletteapp.R
import kim.dongun.paletteapp.adapter.CustomItemDecoration
import kim.dongun.paletteapp.base.BaseActivity
import kim.dongun.paletteapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : BaseActivity() {
    private val viewBinding: ActivityMainBinding by binding(R.layout.activity_main)
    private val vm: MainViewModel by viewModel(qualifier = named("mainViewModel"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = vm
            recyclerView.addItemDecoration(CustomItemDecoration(right = 5f))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionHelper.REQUEST_GALLERY_CODE -> {
                if (PermissionHelper.resultRequestPermissions(results = grantResults))
                    loadImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 101) {
            vm.setImageUrl(url = data?.data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loadImageBtn -> {
                loadImage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadImage() {
        if (galleryPermissionCheck()) {
            val intent = Intent()
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 101)
        }
    }

    private fun galleryPermissionCheck(): Boolean {
        val hasGalleryPermission = PermissionHelper.hasPermission(
            context = this,
            permissions = PermissionHelper.galleryPermissions
        )
        return if (!hasGalleryPermission) {
            PermissionHelper.requestGalleryPermissions(context = this)
            false
        } else true
    }
} 