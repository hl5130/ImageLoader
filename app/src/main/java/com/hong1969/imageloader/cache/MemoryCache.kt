package com.hong1969.imageloader.cache

import android.graphics.Bitmap
import android.support.v4.util.LruCache

/**
 *  内存缓存
 */
class MemoryCache : ImageCache {

    private lateinit var mImageCache: LruCache<String, Bitmap>

    /**
     *  初始化内存缓存
     */
    private fun initLru() {
        // 1、计算可用的最大内存
        // Runtime.getRuntime().maxMemory() 返回的是 字节
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()  // kb

        // 2、取 1/4 做为缓存
        val cacheSize = maxMemory / 4
        mImageCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                if (value == null) return 0
                return value.rowBytes * value.height / 1024
            }
        }
    }


    override fun put(url: String, bitmap: Bitmap) {
        mImageCache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? {
        return mImageCache[url]
    }

    init {
        initLru()
    }

}
