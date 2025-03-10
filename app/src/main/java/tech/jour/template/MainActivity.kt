package tech.jour.template

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.template.base.ktx.observeLiveData
import tech.jour.template.common.ui.BaseActivity
import tech.jour.template.databinding.ActivityMainBinding
import tech.jour.template.module.xpop.toastCenter

/**
 * 首页
 *
 * @since 5/22/21 2:26 PM
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

	/**
	 * 通过 viewModels() + Hilt 获取 ViewModel 实例
	 */
	override val mViewModel by viewModels<MainViewModel>()

	private lateinit var navController: NavController

	override fun initView() {
		val host: NavHostFragment = supportFragmentManager
			.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
			?: return

		navController = host.navController
		NavigationUI.setupWithNavController(mBinding.bottomNavigation, navController);

	}

	override fun initObserve() {
		observeLiveData(mViewModel.data, ::processData)
	}

	private fun processData(data: String) {
//		toast(data)
		toastCenter(this,data)
	}

	override fun initRequestData() {
		// 模拟获取数据
		mViewModel.getData()
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp() || super.onSupportNavigateUp()
	}
}