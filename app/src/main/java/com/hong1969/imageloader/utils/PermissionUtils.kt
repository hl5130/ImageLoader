package com.hong1969.imageloader.utils

import android.Manifest
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.app.Activity





/**
 * Author： 洪亮
 * Time： 2018/6/27 - 上午10:43
 * Email：281332545@qq.com
 * <p>
 * 描述：
 */
object PermissionUtils {
    private const val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    /**
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity
     */
    fun verifyStoragePermissions(activity: Activity) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE)
        }
    }

}