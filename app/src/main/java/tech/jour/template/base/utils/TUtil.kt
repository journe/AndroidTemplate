package tech.jour.template.base.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
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