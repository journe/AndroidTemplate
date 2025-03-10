package tech.jour.template.base.utils

import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tech.jour.template.base.BaseApplication

/**
 * 使用 Flow 做的简单的轮询
 * 请使用单独的协程来进行管理该 Flow
 * Flow 仍有一些操作符是实验性的 使用时需添加 @InternalCoroutinesApi 注解
 * @param intervals 轮询间隔时间/毫秒
 * @param block 需要执行的代码块
 */
suspend fun startPolling(intervals: Long, block: () -> Unit) {
	flow {
		while (true) {
			delay(intervals)
			emit(0)
		}
	}
		.catch { Log.e("flow", "startPolling: $it") }
		.flowOn(Dispatchers.Main)
		.collect { block.invoke() }
}
/**************************************************************************************************/

/**************************************************************************************************/
/**
 * 获取App版本号
 * @return Long App版本号
 */
fun getVersionCode(): Long {
	val packageInfo = BaseApplication.context
		.packageManager
		.getPackageInfo(BaseApplication.context.packageName, 0)
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
		packageInfo.longVersionCode
	} else {
		packageInfo.versionCode.toLong()
	}
}