package com.hong1969.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.hong1969.imageloader.cache.ImageCache
import com.hong1969.imageloader.cache.MemoryCache
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

/**
 * 对外开放的接口类
 */
class ImageLoader {

    // 图片缓存
    private var mImageCache: ImageCache = MemoryCache()

    // 线程池，数量为CPU的数量
    private val mExecutorService: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    // UI Handler
    private val mUiHandler = Handler(Looper.getMainLooper())

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap){
        mUiHandler.post { imageView.setImageBitmap(bitmap) }
    }

    /**
     *  设置 缓存机制
     */
    fun setImageCache(imageCache: ImageCache){
        mImageCache = imageCache
    }

    /**
     *  展示图片
     */
    fun displayImage(url: String, imageView: ImageView) {
        // 如果没有缓存，就从网络获取
        val bitmap = mImageCache.get(url)
        if (bitmap == null) {
            // 网络获取
            submitLoadRequest(url,imageView)
        } else {
            imageView.setImageBitmap(bitmap)
        }
    }

    /**
     *  网络请求
     */
    private fun submitLoadRequest(url: String, imageView: ImageView) {
        imageView.tag = url
        mExecutorService.submit {
            val bitmap = downLoadImage(url) ?: return@submit
            if (imageView.tag == url) updateImageView(imageView,bitmap)
            mImageCache.put(url,bitmap)
        }
    }

    private fun downLoadImage(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val header = (imageUrl.split(":"))[0]
            if (header == "https") {
                val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                bitmap = BitmapFactory.decodeStream(conn.inputStream)
                conn.disconnect()
            } else if (header == "http") {
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                bitmap = BitmapFactory.decodeStream(conn.inputStream)
                conn.disconnect()
            }

        } catch (e: Exception){
            e.printStackTrace()
        }
        return bitmap
    }


}
