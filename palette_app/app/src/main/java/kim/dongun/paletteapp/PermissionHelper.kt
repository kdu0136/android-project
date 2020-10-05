package kim.dongun.paletteapp

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * 권한 확인 클래스
 */
object PermissionHelper {
    val galleryPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    // permission request code
    const val REQUEST_GALLERY_CODE = 1

    /**
     * 권한 허가 확인
     *
     * @param context
     * @param permissions 체크 할 권한 이름 리스트
     *
     * @return Boolean 권한 확인 결과
     */
    fun hasPermission(context: Activity, permissions: Array<String>): Boolean {
        permissions.forEach { permission ->
            // permission not allow
            if (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        // permission allow
        return true
    }

    /**
     * 권한 요청 결과
     *
     * @param results 권한 요청 결과 리스트
     *
     * @return Boolean 권한 요청 결과
     */
    fun resultRequestPermissions(results: IntArray): Boolean {
        results.forEach { result ->
            if (result != PackageManager.PERMISSION_GRANTED)
                return false
        }
        // all permission allow
        return true
    }

    /**
     * 갤러리 권한 요청
     *
     * @param context
     */
    fun requestGalleryPermissions(context: Activity) {
        // request permission higher api 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(context, galleryPermissions, REQUEST_GALLERY_CODE)
        }
    }
}
