package com.hong1969.imageloader.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 *  SD卡 缓存
 */
class DiskCache(private val context: Context) : ImageCache {

    // TODO  本地存储
//
//    private fun file(fileName: String): File {
//        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
//            // 有SD卡
//            val filePath = Environment.getExternalStorageDirectory()
//            return File(filePath,fileName)
//        } else {
//            // 没有SD卡
//            Log.e("DiskCache","没有SD卡")
//            return File("cache/image/$fileName")
//        }
//    }

    /**
     *  位置 ： /storage/emulated/0/Android/data/com.hong1969.imageloader/cache
     */
    private fun file(fileName: String): File {
        val filePath = context.externalCacheDir
        return File(filePath, fileName)
    }

    override fun put(url: String, bitmap: Bitmap) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file(fileName(url)))
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DiskCache", e.message)
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e("DiskCache", e.message)
                }
            }
        }
    }

    override fun get(url: String): Bitmap? {
        val filePath = file(fileName(url)).toString()
        Log.e("DiskCache", filePath)
        return BitmapFactory.decodeFile(filePath)
    }

    private fun fileName(url: String): String {
        val str = url.split("/")
        return str[str.size - 1]
    }


}
