package com.hong1969.imageloader.cache

import android.content.Context
import android.graphics.Bitmap

/**
 *  双缓存模式
 *   先从内存中获取图片，如果没有，再从SD卡中获取
 */
class DoubleCache(context: Context) : ImageCache {

    private val mMemoryCache = MemoryCache()
    private val mDiskCache = DiskCache(context)

    //TODO 本地和内存同时使用

    override fun put(url: String, bitmap: Bitmap) {
        mMemoryCache.put(url,bitmap)
        mDiskCache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        var bitmap = mMemoryCache.get(url)
        if (bitmap == null){
            bitmap = mDiskCache.get(url)
        }
        return bitmap
    }

}
