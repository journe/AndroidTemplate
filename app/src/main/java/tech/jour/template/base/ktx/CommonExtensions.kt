package tech.jour.template.base.ktx

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.permissionx.guolindev.PermissionX
import tech.jour.template.base.utils.toast
import java.text.DecimalFormat

/**
 * Create by Crazy on 2020/12/08
 */

/**
 * 保留两位，末尾不为0
 */
fun Float.keepTwoDecimalsNotZeroEnd(): String =
    this.decimalFormat("#.##")

/**
 * 保留两位，包含0
 */
fun Float.keepTwoDecimals(): String =
    this.decimalFormat("0.00")

fun Float.decimalFormat(formatStr: String): String =
    DecimalFormat(formatStr).format(this)

/**
 * 保留两位，末尾不为0
 */
fun Double.keepTwoDecimalsNotZeroEnd(): String =
    this.decimalFormat("#.##")

/**
 * 保留两位，包含0
 */
fun Double.keepTwoDecimals(): String =
    this.decimalFormat("0.00")

fun Double.decimalFormat(formatStr: String): String =
    DecimalFormat(formatStr).format(this)

fun <T> MutableList<T>.swap(first: Int, second: Int) {
    val tmp = this[first]
    this[first] = this[second]
    this[second] = tmp
}

fun TypedArray.analysisResourceOrColor(styleableId: Int): Int? {
    getResourceId(styleableId, -1).apply { if (this != -1) return resources.getColor(this) }
    getColor(styleableId, -1).apply { if (this != -1) return this }
    return null
}

fun Any?.d() {
    Logger.d(this)
}

fun Fragment.actionWithPermission(permissions: List<String>, action: () -> Unit) {
    PermissionX.init(this)
        .permissions(permissions)
        .request { allGranted, grantedList, deniedList ->
            if (allGranted) {
                action.invoke()
            } else {
                toast("请授予相关权限")
            }

        }
}

fun AppCompatActivity.actionWithPermission(permissions: List<String>, action: () -> Unit) {
    PermissionX.init(this)
        .permissions(permissions)
        .request { allGranted, grantedList, deniedList ->
            if (allGranted) {
                action.invoke()
            } else {
                toast("请授予相关权限")
            }

        }
}