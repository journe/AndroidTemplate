package tech.jour.template.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import tech.jour.template.base.app.ActivityLifecycleCallbacksImpl
import tech.jour.template.base.app.LoadModuleProxy
import tech.jour.template.base.utils.CoilGIFImageLoader
import kotlin.system.measureTimeMillis

/**
 * Application 基类
 *
 * @since 4/24/21 5:30 PM
 */
open class BaseApplication : Application(), ImageLoaderFactory {

	private val mCoroutineScope by lazy(mode = LazyThreadSafetyMode.NONE) { MainScope() }

	private val mLoadModuleProxy by lazy(mode = LazyThreadSafetyMode.NONE) { LoadModuleProxy() }

	companion object {
		// 全局Context
		@SuppressLint("StaticFieldLeak")
		lateinit var context: Context

		@SuppressLint("StaticFieldLeak")
		lateinit var application: BaseApplication
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		context = base
		application = this
		mLoadModuleProxy.onAttachBaseContext(base)
	}

	override fun onCreate() {
		super.onCreate()

		// 全局监听 Activity 生命周期
		registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())

		mLoadModuleProxy.onCreate(this)

		// 策略初始化第三方依赖
		initDepends()
	}

	/**
	 * 初始化第三方依赖
	 */
	private fun initDepends() {
		// 开启一个 Default Coroutine 进行初始化不会立即使用的第三方
		mCoroutineScope.launch(Dispatchers.Default) {
			mLoadModuleProxy.initByBackstage()
		}

		// 前台初始化
		val allTimeMillis = measureTimeMillis {
			val depends = mLoadModuleProxy.initByFrontDesk()
			var dependInfo: String
			depends.forEach {
				val dependTimeMillis = measureTimeMillis { dependInfo = it() }
				Log.d("BaseApplication", "initDepends: $dependInfo : $dependTimeMillis ms")
			}
		}
		Log.d("BaseApplication", "初始化完成 $allTimeMillis ms")
	}

	override fun onTerminate() {
		super.onTerminate()
		mLoadModuleProxy.onTerminate(this)
		mCoroutineScope.cancel()
	}
	override fun newImageLoader(): ImageLoader {
		return CoilGIFImageLoader.imageLoader
	}
}