package tech.jour.template.module.xpop

import android.content.Context
import com.lxj.xpopup.core.CenterPopupView
import tech.jour.template.R
import tech.jour.template.databinding.PopupLoadingBinding

class LoadingPopup(context: Context) :
	CenterPopupView(context) {
	lateinit var mBinding: PopupLoadingBinding

	override fun getImplLayoutId(): Int {
		return R.layout.popup_loading
	}

	override fun onCreate() {
		super.onCreate()
		mBinding = PopupLoadingBinding.bind(popupImplView)
	}

}