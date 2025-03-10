package tech.jour.template.module.xpop

import android.content.Context
import androidx.core.view.postDelayed
import com.lxj.xpopup.core.CenterPopupView
import tech.jour.template.R
import tech.jour.template.databinding.PopupToastBinding

class CenterPopup(context: Context, private val popTextStr: String = "") :
	CenterPopupView(context) {
	lateinit var mBinding: PopupToastBinding

	override fun getImplLayoutId(): Int {
		return R.layout.popup_toast
	}

	override fun onCreate() {
		super.onCreate()
		mBinding = PopupToastBinding.bind(popupImplView)
		mBinding.content.text = popTextStr
		postDelayed(1000L) { dismiss() }
	}

}