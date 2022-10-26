package tech.jour.template

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.template.base.ktx.observeLiveData
import tech.jour.template.base.utils.toast
import tech.jour.template.common.ui.BaseActivity
import tech.jour.template.databinding.ActivityMainBinding

/**
 * 首页
 *
 * @author Qu Yunshuo
 * @since 5/22/21 2:26 PM
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    /**
     * 通过 viewModels() + Hilt 获取 ViewModel 实例
     */
    override val mViewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController

    override fun ActivityMainBinding.initView() {
        val binding = mBinding
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
            ?: return

        navController = host.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

    }

    override fun initObserve() {
        observeLiveData(mViewModel.data, ::processData)
    }

    private fun processData(data: String) {
        toast(data)
//        mBinding.vTvHello.text = data
//        mBinding.vTvHello.setTextColor(Color.BLUE)
    }

    override fun initRequestData() {
        // 模拟获取数据
        mViewModel.getData()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}