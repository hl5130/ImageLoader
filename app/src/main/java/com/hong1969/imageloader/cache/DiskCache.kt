package com.hong1969.imageloader.cache

import android.graphics.Bitmap

class DiskCache : ImageCache {

    // TODO  本地存储

    override fun put(url: String, bitmap: Bitmap) {

    }

    override fun get(url: String): Bitmap? {
        return null
    }

}
