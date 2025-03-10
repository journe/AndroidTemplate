package tech.jour.template.module.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import tech.jour.template.R
import tech.jour.template.base.ktx.invisible
import tech.jour.template.base.ktx.visible
import tech.jour.template.databinding.LayoutHomeHeadCckBinding

//自定义view示例
class CustomView : ConstraintLayout {

	lateinit var mBinding: LayoutHomeHeadCckBinding

	constructor(context: Context) : super(context) {
		init(context)
	}

	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
		init(context)
	}

	constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
		context,
		attrs,
		defStyle
	) {
		init(context)
	}

	private fun init(context: Context) {
		if (isInEditMode) {
			LayoutInflater.from(context).inflate(R.layout.layout_home_head_cck, this, true)
			return
		}
		mBinding = LayoutHomeHeadCckBinding.inflate(LayoutInflater.from(context), this, true)
	}

	fun setUser() {
		mBinding.unloginText.visible()
		mBinding.loginGroup.invisible()
	}

}