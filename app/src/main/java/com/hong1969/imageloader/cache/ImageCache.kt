package com.hong1969.imageloader.cache

import android.graphics.Bitmap

/**
 * 图片缓存类
 */
interface ImageCache {

    fun put(url: String, bitmap: Bitmap)

    fun get(url: String): Bitmap?

}
