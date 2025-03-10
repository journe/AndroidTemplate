package tech.jour.template.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
//import com.alibaba.android.arouter.launcher.ARouter
import tech.jour.template.base.mvvm.vm.BaseViewModel
import tech.jour.template.base.utils.TUtil

/**
 * Fragment基类
 *
 *
 * @since 8/27/20
 */
abstract class BaseFrameFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment(),
	FrameView {

	/**
	 * 私有的 ViewBinding 此写法来自 Google Android 官方
	 */
	private var _binding: VB? = null

	protected val mBinding get() = _binding!!

	protected abstract val mViewModel: VM

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		createBinding()
		return _binding?.root
	}

	private fun createBinding() {
		val clazzBD: Class<VB> = TUtil.getClazz<Class<VB>>(this, 0)
		_binding = clazzBD.getMethod("inflate", LayoutInflater::class.java)
			.invoke(null, layoutInflater) as VB
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		// ARouter 依赖注入
//        ARouter.getInstance().inject(this)

		initView()
		initObserve()
		initRequestData()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onDestroy() {
		super.onDestroy()
	}


}