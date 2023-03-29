package tech.jour.template.base.ktx

import com.blankj.utilcode.util.TimeUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * 格式化单位
 *
 * @return
 */
fun Long.getFormatSize(): String {
    val kiloByte = this / 1024
    if (kiloByte < 1) {
        //            return size + "Byte";
        return "0K"
    }

    val megaByte = kiloByte / 1024
    if (megaByte < 1) {
        val result1 = BigDecimal(kiloByte)
        return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
            .toPlainString() + "KB"
    }

    val gigaByte = megaByte / 1024
    if (gigaByte < 1) {
        val result2 = BigDecimal(megaByte)
        return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
            .toPlainString() + "MB"
    }

    val teraBytes = gigaByte / 1024
    if (teraBytes < 1) {
        val result3 = BigDecimal(gigaByte)
        return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
            .toPlainString() + "GB"
    }
    val result4 = BigDecimal(teraBytes)
    return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
}

fun String.isMobileNo(): Boolean {
    if (this.length != 11) return false
    val mobilePattern: Pattern =
        Pattern.compile("^[1][3456789][0-9]{9}\$")
    return mobilePattern.matcher(this).matches()
}

fun String.isPassword(): Boolean {
    val mobilePattern: Pattern =
        Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}\$")
    return mobilePattern.matcher(this).matches()
}

fun Int.toDuration(): String {
    return if (this < 60) {
        this.toString() + "秒"
    } else {
        val min = this / 60
        val s = this % 60
        min.toString() + "分" + s.toString() + "秒"
    }
}

fun String.keyMaps(): Map<String, Any> {
    val gson = Gson()
    val type = object : TypeToken<Map<String?, Any?>?>() {}.type
    return gson.fromJson(this, type)
}

fun String.filename() = this.substring(this.lastIndexOf("/") + 1)


fun String.getUrlFileName() = this.substring(this.lastIndexOf("/") + 1)

fun String.getVipDate(): String =
    TimeUtils.millis2String(TimeUtils.string2Millis(this), "yyyy-MM-dd")
