package tech.jour.template.app

import dagger.hilt.android.HiltAndroidApp
import tech.jour.template.base.BaseApplication

@HiltAndroidApp
class AppApplication : BaseApplication() {

	override fun onCreate() {
		super.onCreate()
	}
}