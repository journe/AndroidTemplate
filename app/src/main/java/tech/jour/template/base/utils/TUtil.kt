package tech.jour.template.base.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by yzy on 2020-07-08 14:41
 * Email: yzytmac@163.com
 * Phone: 18971165201
 * QQ: 398564331
 * Description: 泛型工具类
 */
object TUtil {
    /**
     * 获取泛型对象
     */
    fun <T> getInstance(obj: Any, index: Int): T {
        return (getType(obj, index) as Class<T>).newInstance()
    }

    /**
     * 获取泛型字节码
     */
    fun <T : Class<*>?> getClazz(obj: Any, i: Int): T {
        return getType(obj, i) as T
    }

    private fun getType(obj: Any, i: Int): Type {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[i]
    }

}