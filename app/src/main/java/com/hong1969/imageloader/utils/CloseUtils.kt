package com.hong1969.imageloader.utils

import android.util.Log
import java.io.Closeable
import java.io.IOException

/**
 * Author： 洪亮
 * Time： 2018/6/27 - 下午2:42
 * Email：281332545@qq.com
 * <p>
 * 描述：
 */
class CloseUtils {
    companion object {

        /**
         *  关闭 Closeable 的对象
         *  @param closeable
         */
        fun closeQuietly(closeable: Closeable?){
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException){
                    e.printStackTrace()
                    Log.e("CloseUtils","关闭失败：${e.message}")
                }
            }
        }
    }
}