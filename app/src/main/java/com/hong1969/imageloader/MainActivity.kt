package com.hong1969.imageloader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hong1969.imageloader.cache.DoubleCache
import com.hong1969.imageloader.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val imageLoader = ImageLoader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionUtils.verifyStoragePermissions(this)
        imageLoader.setImageCache(DoubleCache(this))
        imageLoader.displayImage("https://www.baidu.com/img/bd_logo1.png",imageView)
    }
}
