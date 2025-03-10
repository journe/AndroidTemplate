package tech.jour.template.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.google.auto.service.AutoService
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import tech.jour.template.BuildConfig
import tech.jour.template.base.BaseApplication
import tech.jour.template.base.app.ApplicationLifecycle
import tech.jour.template.base.utils.ForegroundBackgroundObserver
import tech.jour.template.base.utils.ProcessUtils
import tech.jour.template.base.utils.network.NetworkStateClient

/**
 * 项目相关的Application
 *
 * @since 4/16/21 3:37 PM
 */
@AutoService(ApplicationLifecycle::class)
class CommonApplication : ApplicationLifecycle, ForegroundBackgroundObserver {

	companion object {
		// 全局CommonApplication
		@SuppressLint("StaticFieldLeak")
		lateinit var mCommonApplication: CommonApplication
	}

	/**
	 * 同[Application.attachBaseContext]
	 * @param context Context
	 */
	override fun onAttachBaseContext(context: Context) {
		mCommonApplication = this
	}

	/**
	 * 同[Application.onCreate]
	 * @param application Application
	 */
	override fun onCreate(application: Application) {}

	/**
	 * 同[Application.onTerminate]
	 * @param application Application
	 */
	override fun onTerminate(application: Application) {}

	/**
	 * 主线程前台初始化
	 * @return MutableList<() -> String> 初始化方法集合
	 */
	override fun initByFrontDesk(): MutableList<() -> String> {
		val list = mutableListOf<() -> String>()
		// 以下只需要在主进程当中初始化 按需要调整
		if (ProcessUtils.isMainProcess(BaseApplication.context)) {
			list.add { initMMKV() }
			list.add { initNetworkStateClient() }
		}
		list.add { initTencentBugly() }
		return list
	}

	/**
	 * 不需要立即初始化的放在这里进行后台初始化
	 */
	override fun initByBackstage() {
//        initX5WebViewCore()
		initLogger()
	}

	/**
	 * 初始化网络状态监听客户端
	 * @return Unit
	 */
	private fun initNetworkStateClient(): String {
		NetworkStateClient.register()
		return "NetworkStateClient -->> init complete"
	}


	/**
	 * 腾讯 MMKV 初始化
	 */
	private fun initMMKV(): String {
//        val result = SpUtils.initMMKV(BaseApplication.context)
		return "MMKV -->> "
	}


	/**
	 * 初始化 腾讯Bugly
	 * 测试环境应该与正式环境的日志收集渠道分隔开
	 * 目前有两个渠道 测试版本/正式版本
	 */
	private fun initTencentBugly(): String {
		// 第三个参数为SDK调试模式开关
//        CrashReport.initCrashReport(
//            BaseApplication.context,
//            BaseApplication.context.getString(R.string.BUGLY_APP_ID),
//            BuildConfig.VERSION_TYPE != VersionStatus.RELEASE
//        )
		return "Bugly -->> init complete"
	}

	private fun initLogger(): String {
		Logger.addLogAdapter(object :
			AndroidLogAdapter(
				PrettyFormatStrategy.newBuilder().tag("jour").build()
			) {
			override fun isLoggable(priority: Int, tag: String?): Boolean {
				return BuildConfig.DEBUG
			}
		})
		return "Logger -->> init complete"
	}

	override fun foregroundBackgroundNotify(isForeground: Boolean) {
		Logger.d("isForeground: $isForeground")
	}
}